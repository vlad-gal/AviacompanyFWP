package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AircraftDaoImpl implements AircraftDao {

    @Override
    public List<Aircraft> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRCRAFT)) {
            ResultSet resultSet = statement.executeQuery();
            return createAircrafts(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all aircrafts", e);
        }
    }

    @Override
    public List<Aircraft> findAllByStatus(Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRCRAFT_BY_STATUS)) {
            statement.setInt(1, status.ordinal());
            ResultSet resultSet = statement.executeQuery();
            return createAircrafts(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all aircrafts", e);
        }
    }

    @Override
    public Optional<Aircraft> findById(int aircraftId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AIRCRAFT_BY_ID)) {
            statement.setInt(1, aircraftId);
            ResultSet resultSet = statement.executeQuery();
            Aircraft aircraft = null;
            EntityFactory factory = EntityFactory.getInstance();
            while (resultSet.next()) {
                Map<String, Object> aircraftData = createAircraftData(resultSet);
                aircraft = factory.getAircraftCreator().create(aircraftData);
                logger.log(Level.DEBUG, "Aircraft found: {}", aircraft);
            }
            return Optional.ofNullable(aircraft);
        } catch (SQLException e) {
            throw new DaoException("Error while finding aircraft by id", e);
        }
    }

    @Override
    public boolean addAircraft(Aircraft aircraft) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_AIRCRAFT)) {
            statement.setString(1, aircraft.getTailNumber());
            statement.setString(2, aircraft.getAircraftName());
            statement.setInt(3, aircraft.getAircraftType().ordinal());
            statement.setInt(4, aircraft.getStatus().ordinal());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while adding aircraft", e);
        }
    }

    @Override
    public boolean update(Aircraft aircraft) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_AIRCRAFT)) {
            statement.setString(1, aircraft.getTailNumber());
            statement.setString(2, aircraft.getAircraftName());
            statement.setInt(3, aircraft.getAircraftType().ordinal());
            statement.setInt(4, aircraft.getStatus().ordinal());
            statement.setInt(5, aircraft.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating aircraft", e);
        }
    }

    @Override
    public boolean remove(int aircraftId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_AIRCRAFT_BY_ID)) {
            statement.setInt(1, Status.INACTIVE.ordinal());
            statement.setInt(2, aircraftId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing aircraft", e);
        }
    }

    private List<Aircraft> createAircrafts(ResultSet resultSet) throws SQLException {
        List<Aircraft> aircrafts = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> aircraftData = createAircraftData(resultSet);
            Aircraft aircraft = EntityFactory.getInstance().getAircraftCreator().create(aircraftData);
            logger.log(Level.DEBUG, "Aircraft found: {}", aircraft);
            aircrafts.add(aircraft);
        }
        return aircrafts;
    }

    private Map<String, Object> createAircraftData(ResultSet resultSet) throws SQLException {
        Map<String, Object> aircraftData = new HashMap<>();
        aircraftData.put(ColumnName.AIRCRAFT_ID, resultSet.getInt(1));
        aircraftData.put(ColumnName.TAIL_NUMBER, resultSet.getString(2));
        aircraftData.put(ColumnName.AIRCRAFT_NAME, resultSet.getString(3));
        aircraftData.put(ColumnName.AIRCRAFT_TYPE, resultSet.getString(4).toUpperCase());
        aircraftData.put(ColumnName.STATUS_NAME, resultSet.getString(5).toUpperCase());
        return aircraftData;
    }
}
