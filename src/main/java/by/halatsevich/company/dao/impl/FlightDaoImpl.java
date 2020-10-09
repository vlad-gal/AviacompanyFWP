package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.FlightDao;
import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.factory.EntityFactory;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.Flight;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FlightDaoImpl extends MySqlDao implements FlightDao {

    @Override
    public List<Flight> selectAllFlights() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_FLIGHTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                Flight flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return flights;
    }

    private Map<String, Object> createFlightData(ResultSet resultSet) throws SQLException {
        Map<String, Object> flightData = new HashMap<>();
        flightData.put(SqlColumnName.FLIGHT_ID, resultSet.getInt(1));
        flightData.put(SqlColumnName.DEPARTURE_AIRPORT_ID, resultSet.getInt(2));
        flightData.put(SqlColumnName.DESTINATION_AIRPORT_ID, resultSet.getInt(3));
        flightData.put(SqlColumnName.DEPART_TIME, resultSet.getLong(4));
        flightData.put(SqlColumnName.ARRIVE_TIME, resultSet.getLong(5));
        flightData.put(SqlColumnName.AIRCRAFT_ID, resultSet.getInt(6));
        flightData.put(SqlColumnName.CREW_ID, resultSet.getInt(7));
        flightData.put(SqlColumnName.OPERATOR_ID, resultSet.getInt(8));
        flightData.put(SqlColumnName.ENTITY_NAME, Flight.class.getSimpleName());
        return flightData;
    }

    @Override
    public Optional<Flight> selectFlightByDepartureAirportId(int departureAirportId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPARTURE_AIRPORT_ID);
            statement.setInt(1, departureAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> selectFlightByDestinationAirportId(int destinationAirportId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DESTINATION_AIRPORT_ID);
            statement.setInt(1, destinationAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> selectFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPART_PERIOD_OF_TIME);
            statement.setLong(1, departTimeFrom);
            statement.setLong(2, departTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    public Optional<Flight> selectFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_ARRIVE_PERIOD_OF_TIME);
            statement.setLong(1, arriveTimeFrom);
            statement.setLong(2, arriveTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }


    @Override
    public Optional<Flight> selectFlightByCrewId(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_CREW_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> selectFlightByOperatorId(int operatorId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory<Flight> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_OPERATOR_ID);
            statement.setInt(1, operatorId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.createEntity(flightData);
                logger.log(Level.DEBUG, "Flight was found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all flights", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public boolean addFlight(Flight flight) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_FLIGHT);
            statement.setInt(1, flight.getDeparture().getId());
            statement.setInt(2, flight.getDestination().getId());
            statement.setLong(3, flight.getDepartTime());
            statement.setLong(4, flight.getArriveTime());
            statement.setInt(5, flight.getOperator().getId());
            statement.setInt(6, flight.getCrew().getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isAdded = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeStatement(statement);
        }
        return isAdded;
    }

    @Override
    public Optional<Flight> updateFlight(Flight flight) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_FLIGHT);
            statement.setInt(1, flight.getDeparture().getId());
            statement.setInt(2, flight.getDestination().getId());
            statement.setLong(3, flight.getDepartTime());
            statement.setLong(4, flight.getArriveTime());
            statement.setInt(5, flight.getOperator().getId());
            statement.setInt(6, flight.getCrew().getId());
            statement.setInt(7, flight.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public boolean removeFlight(int flightId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_FLIGHT_BY_ID);
            statement.setInt(1, flightId);
            int update = statement.executeUpdate();
            if (update == 1) {
                isRemoved = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while deleting user", e);
        } finally {
            closeStatement(statement);
        }
        return isRemoved;
    }
}
