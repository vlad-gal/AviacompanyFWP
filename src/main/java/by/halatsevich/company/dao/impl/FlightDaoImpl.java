package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.FlightDao;
import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.factory.EntityFactory;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.Flight;
import by.halatsevich.company.entity.Status;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FlightDaoImpl implements FlightDao {

    @Override
    public List<Flight> findAllFlights() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_FLIGHTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                Flight flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flights found: {}", flight);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all flights", e);
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
        flightData.put(SqlColumnName.OPERATOR_ID, resultSet.getInt(7));
        flightData.put(SqlColumnName.CREW_ID, resultSet.getInt(8));
        flightData.put(SqlColumnName.STATUS_NAME, resultSet.getString(9).toUpperCase());
        return flightData;
    }

    @Override
    public Optional<Flight> findFlightByDepartureAirportId(int departureAirportId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPARTURE_AIRPORT_ID);
            statement.setInt(1, departureAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by departure airport id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> findFlightByDestinationAirportId(int destinationAirportId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DESTINATION_AIRPORT_ID);
            statement.setInt(1, destinationAirportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by destination airport id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_DEPART_PERIOD_OF_TIME);
            statement.setLong(1, departTimeFrom);
            statement.setLong(2, departTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by depart time", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    public Optional<Flight> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_ARRIVE_PERIOD_OF_TIME);
            statement.setLong(1, arriveTimeFrom);
            statement.setLong(2, arriveTimeTo);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by arrive time", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> findFlightByCrewId(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_CREW_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by crew id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(flight);
    }

    @Override
    public Optional<Flight> findFlightByOperatorId(int operatorId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_FLIGHT_BY_OPERATOR_ID);
            statement.setInt(1, operatorId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> flightData = createFlightData(resultSet);
                flight = factory.getFlightCreator().create(flightData);
                logger.log(Level.DEBUG, "Flight found: {}", flight);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding flight by operator id", e);
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
            statement.setInt(1, flight.getDepartureAirportId());
            statement.setInt(2, flight.getDestinationAirportId());
            statement.setLong(3, flight.getDepartTime());
            statement.setLong(4, flight.getArriveTime());
            statement.setLong(5, flight.getAircraftId());
            statement.setInt(6, flight.getOperatorId());
            statement.setInt(7, flight.getCrewId());
            int statusId = flight.getStatus().ordinal() + 1;
            statement.setInt(8, statusId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did flight add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while inserting flight", e);
        } finally {
            closeStatement(statement);
        }
        return isAdded;
    }

    @Override
    public boolean updateFlight(Flight flight) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_FLIGHT);
            statement.setInt(1, flight.getDepartureAirportId());
            statement.setInt(2, flight.getDestinationAirportId());
            statement.setLong(3, flight.getDepartTime());
            statement.setLong(4, flight.getArriveTime());
            statement.setLong(5, flight.getAircraftId());
            statement.setInt(6, flight.getOperatorId());
            statement.setInt(7, flight.getCrewId());
            int statusId = flight.getStatus().ordinal() + 1;
            statement.setInt(8, statusId);
            statement.setInt(9, flight.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did flight update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating flight", e);
        } finally {
            closeStatement(statement);
        }
        return isUpdated;
    }

    @Override
    public boolean removeFlight(int flightId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
        }
        return isRemoved;
    }
}
