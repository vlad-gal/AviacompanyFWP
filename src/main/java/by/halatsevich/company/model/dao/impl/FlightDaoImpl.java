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

public class FlightDaoImpl implements FlightDao {

    @Override
    public List<FlightDto> findAll() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<FlightDto> flightDtos;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_FLIGHTS);
            resultSet = statement.executeQuery();
            flightDtos = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                FlightDto flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flightDto);
                flightDtos.add(flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all flight", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return flightDtos;
    }

    @Override
    public Optional<FlightDto> findById(int flightId) throws DaoException { // TODO: 20.10.2020 test method
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_ID);
            statement.setInt(1, flightId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
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

    @Override
    public Optional<FlightDto> findFlightByDepartureAirportId(int departureAirportId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPARTURE_AIRPORT_ID);
            statement.setInt(1, departureAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by departure airport id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    @Override
    public Optional<FlightDto> findFlightByDestinationAirportId(int destinationAirportId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DESTINATION_AIRPORT_ID);
            statement.setInt(1, destinationAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by destination airport id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    @Override
    public Optional<FlightDto> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPART_PERIOD_OF_TIME);
            statement.setLong(1, departTimeFrom);
            statement.setLong(2, departTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by depart time", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    public Optional<FlightDto> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_ARRIVE_PERIOD_OF_TIME);
            statement.setLong(1, arriveTimeFrom);
            statement.setLong(2, arriveTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by arrive time", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    @Override
    public Optional<FlightDto> findFlightByCrewId(int crewId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_CREW_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by crew id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    @Override
    public Optional<FlightDto> findFlightByOperatorId(int operatorId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        FlightDto flightDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_OPERATOR_ID);
            statement.setInt(1, operatorId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flightDto = factory.getFlightDtoCreator().create(flightData);
                logger.log(Level.DEBUG, "FlightDto found: {}", flightDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flightDto by operator id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(flightDto);
    }

    @Override
    public boolean addFlight(FlightDto flightDto) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_FLIGHT);
            statement.setInt(1, flightDto.getDepartureAirportId());
            statement.setInt(2, flightDto.getDestinationAirportId());
            statement.setLong(3, flightDto.getDepartTime());
            statement.setLong(4, flightDto.getArriveTime());
            statement.setLong(5, flightDto.getAircraftId());
            statement.setInt(6, flightDto.getOperatorId());
            statement.setInt(7, flightDto.getCrewId());
            int statusId = flightDto.getStatus().ordinal() + 1;
            statement.setInt(8, statusId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did flightDto add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while inserting flightDto", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean update(FlightDto flightDto) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_FLIGHT);
            statement.setInt(1, flightDto.getDepartureAirportId());
            statement.setInt(2, flightDto.getDestinationAirportId());
            statement.setLong(3, flightDto.getDepartTime());
            statement.setLong(4, flightDto.getArriveTime());
            statement.setLong(5, flightDto.getAircraftId());
            statement.setInt(6, flightDto.getOperatorId());
            statement.setInt(7, flightDto.getCrewId());
            int statusId = flightDto.getStatus().ordinal() + 1;
            statement.setInt(8, statusId);
            statement.setInt(9, flightDto.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did flightDto update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating flightDto", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean remove(int flightId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_FLIGHT_BY_ID);
            int statusId = Status.INACTIVE.ordinal() + 1;
            statement.setInt(1, statusId);
            statement.setInt(2, flightId);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isRemoved = true;
            }
            logger.log(Level.DEBUG, "Did flight remove? {}", isRemoved);
        } catch (SQLException e) {
            throw new DaoException("Error while removing flight", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isRemoved;
    }
}
