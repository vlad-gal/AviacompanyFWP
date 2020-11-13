package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.dao.FlightDao;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.entity.FlightDto;
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

/**
 * The class represents flight dao implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class FlightDaoImpl implements FlightDao {

    @Override
    public List<FlightDto> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_FLIGHTS)) {
            ResultSet resultSet = statement.executeQuery();
            return createFlightDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all flights", e);
        }
    }

    @Override
    public List<FlightDto> findAllByStatus(Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_FLIGHTS_BY_STATUS)) {
            statement.setInt(1, status.ordinal());
            ResultSet resultSet = statement.executeQuery();
            return createFlightDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all flights by status", e);
        }
    }

    @Override
    public List<FlightDto> findUserFlightsByStatus(int userId, Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS_FLIGHTS_BY_STATUS)) {
            statement.setInt(1, status.ordinal());
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            return createFlightDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all flights by status", e);
        }
    }

    @Override
    public Optional<FlightDto> findById(int flightId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_ID)) {
            statement.setInt(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            return createFlightDto(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by id", e);
        }
    }

    @Override
    public boolean addFlight(FlightDto flightDto) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_FLIGHT)) {
            statement.setInt(1, flightDto.getDepartureAirportId());
            statement.setInt(2, flightDto.getDestinationAirportId());
            statement.setLong(3, flightDto.getDepartTime());
            statement.setLong(4, flightDto.getArriveTime());
            statement.setLong(5, flightDto.getAircraftId());
            statement.setInt(6, flightDto.getOperatorId());
            statement.setInt(7, flightDto.getCrewId());
            statement.setInt(8, flightDto.getStatus().ordinal());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while inserting flight", e);
        }
    }

    @Override
    public boolean update(FlightDto flightDto) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FLIGHT)) {
            statement.setInt(1, flightDto.getDepartureAirportId());
            statement.setInt(2, flightDto.getDestinationAirportId());
            statement.setLong(3, flightDto.getDepartTime());
            statement.setLong(4, flightDto.getArriveTime());
            statement.setLong(5, flightDto.getAircraftId());
            statement.setInt(6, flightDto.getOperatorId());
            statement.setInt(7, flightDto.getCrewId());
            statement.setInt(8, flightDto.getStatus().ordinal());
            statement.setInt(9, flightDto.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating flight", e);
        }
    }

    @Override
    public boolean remove(int flightId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_FLIGHT_BY_ID)) {
            statement.setInt(1, Status.INACTIVE.ordinal());
            statement.setInt(2, flightId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing flight", e);
        }
    }

    private Map<String, Object> createFlightData(ResultSet resultSet) throws SQLException {
        Map<String, Object> flightData = new HashMap<>();
        flightData.put(ColumnName.FLIGHT_ID, resultSet.getInt(1));
        flightData.put(ColumnName.DEPARTURE_AIRPORT_ID, resultSet.getInt(2));
        flightData.put(ColumnName.DESTINATION_AIRPORT_ID, resultSet.getInt(3));
        flightData.put(ColumnName.DEPART_TIME, resultSet.getLong(4));
        flightData.put(ColumnName.ARRIVE_TIME, resultSet.getLong(5));
        flightData.put(ColumnName.AIRCRAFT_ID, resultSet.getInt(6));
        flightData.put(ColumnName.OPERATOR_ID, resultSet.getInt(7));
        flightData.put(ColumnName.CREW_ID, resultSet.getInt(8));
        flightData.put(ColumnName.STATUS_NAME, resultSet.getString(9).toUpperCase());
        return flightData;
    }

    private Optional<FlightDto> createFlightDto(ResultSet resultSet) throws SQLException {
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        while (resultSet.next()) {
            Map<String, Object> flightData = createFlightData(resultSet);
            flightDto = factory.getFlightDtoCreator().create(flightData);
            logger.log(Level.DEBUG, "Flight found: {}", flightDto);
        }
        return Optional.ofNullable(flightDto);
    }

    private List<FlightDto> createFlightDtos(ResultSet resultSet) throws SQLException {
        List<FlightDto> flightDtos = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        while (resultSet.next()) {
            Map<String, Object> flightData = createFlightData(resultSet);
            FlightDto flightDto = factory.getFlightDtoCreator().create(flightData);
            logger.log(Level.DEBUG, "Flight found: {}", flightDto);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }
}

