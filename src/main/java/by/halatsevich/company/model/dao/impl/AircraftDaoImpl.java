package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.SqlColumnName;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.dao.pool.ConnectionPool;
import by.halatsevich.company.model.entity.Aircraft;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AircraftDaoImpl implements AircraftDao {

    @Override
    public List<Aircraft> findAllAircrafts() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aircraft> aircrafts = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRCRAFT);
            resultSet = statement.executeQuery();
            createAircraft(resultSet, aircrafts, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all aircrafts", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return aircrafts;
    }

    private Map<String, Object> createAircraftData(ResultSet resultSet) throws SQLException {
        Map<String, Object> aircraftData = new HashMap<>();
        aircraftData.put(SqlColumnName.AIRCRAFT_ID, resultSet.getInt(1));
        aircraftData.put(SqlColumnName.TAIL_NUMBER, resultSet.getString(2));
        aircraftData.put(SqlColumnName.AIRCRAFT_NAME, resultSet.getString(3));
        aircraftData.put(SqlColumnName.AIRCRAFT_TYPE, resultSet.getString(4).toUpperCase());
        return aircraftData;
    }

    @Override
    public Optional<Aircraft> findAircraftById(int aircraftId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Aircraft aircraft = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRCRAFT_BY_ID);
            statement.setInt(1, aircraftId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> aircraftData = createAircraftData(resultSet);
                aircraft = factory.getAircraftCreator().create(aircraftData);
                logger.log(Level.DEBUG, "Aircraft found: {}", aircraft);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding aircraft by id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(aircraft);
    }

    @Override
    public Optional<Aircraft> findAircraftByTailNumber(String tailNumber) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Aircraft aircraft = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRCRAFT_BY_TAIL_NUMBER);
            statement.setString(1, tailNumber);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> aircraftData = createAircraftData(resultSet);
                aircraft = factory.getAircraftCreator().create(aircraftData);
                logger.log(Level.DEBUG, "Aircraft found: {}", aircraft);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding aircraft by tail number", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(aircraft);
    }

    @Override
    public List<Aircraft> findAircraftsByName(String aircraftName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aircraft> aircrafts = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRCRAFTS_BY_NAME);
            statement.setString(1, aircraftName);
            resultSet = statement.executeQuery();
            createAircraft(resultSet, aircrafts, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding aircrafts by name", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return aircrafts;
    }

    @Override
    public List<Aircraft> findAircraftsByType(Aircraft.AircraftType aircraftType) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aircraft> aircrafts = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRCRAFTS_BY_TYPE);
            int aircraftTypeId = aircraftType.ordinal() + 1;
            statement.setInt(1, aircraftTypeId);
            resultSet = statement.executeQuery();
            createAircraft(resultSet, aircrafts, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding aircrafts by type", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return aircrafts;
    }

    private void createAircraft(ResultSet resultSet, List<Aircraft> aircrafts, EntityFactory factory) throws SQLException {
        while (resultSet.next()) {
            Map<String, Object> aircraftData = createAircraftData(resultSet);
            Aircraft aircraft = factory.getAircraftCreator().create(aircraftData);
            logger.log(Level.DEBUG, "Aircraft found: {}", aircraft);
            aircrafts.add(aircraft);
        }
    }

    @Override
    public boolean addAircraft(Aircraft aircraft) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_AIRCRAFT);
            statement.setString(1, aircraft.getTailNumber());
            statement.setString(2, aircraft.getAircraftName());
            int aircraftTypeId = aircraft.getAircraftType().ordinal() + 1;
            statement.setInt(3, aircraftTypeId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did aircraft add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while adding aircraft", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean updateAircraft(Aircraft aircraft) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_AIRCRAFT);
            statement.setString(1, aircraft.getTailNumber());
            statement.setString(2, aircraft.getAircraftName());
            int aircraftTypeId = aircraft.getAircraftType().ordinal() + 1;
            statement.setInt(3, aircraftTypeId);
            statement.setInt(4, aircraft.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did aircraft update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating aircraft", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }
}
