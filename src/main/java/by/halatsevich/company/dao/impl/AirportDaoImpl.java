package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.AirportDao;
import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.factory.EntityFactory;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.Airport;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AirportDaoImpl implements AirportDao {

    @Override
    public List<Airport> findAllAirports() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRPORTS);
            resultSet = statement.executeQuery();
            createAirports(resultSet, airports, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all airports", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return airports;
    }

    private Map<String, Object> createAirportData(ResultSet resultSet) throws SQLException {
        Map<String, Object> airportData = new HashMap<>();
        airportData.put(SqlColumnName.AIRPORT_ID, resultSet.getInt(1));
        airportData.put(SqlColumnName.AIRPORT_NAME, resultSet.getString(2));
        airportData.put(SqlColumnName.COUNTRY, resultSet.getString(3));
        airportData.put(SqlColumnName.CITY, resultSet.getString(4));
        return airportData;
    }

    @Override
    public Optional<Airport> findAirportById(int airportId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Airport airport = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORT_BY_ID);
            statement.setInt(1, airportId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> airportData = createAirportData(resultSet);
                airport = factory.getAirportCreator().create(airportData);
                logger.log(Level.DEBUG, "Airport found: {}", airport);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding airport by id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(airport);
    }

    @Override
    public Optional<Airport> findAirportByName(String airportName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Airport airport = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORT_BY_NAME);
            statement.setString(1, airportName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> airportData = createAirportData(resultSet);
                airport = factory.getAirportCreator().create(airportData);
                logger.log(Level.DEBUG, "Airport found: {}", airport);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding airport by name", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(airport);
    }

    @Override
    public List<Airport> findAirportsByCity(String cityName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORTS_BY_CITY);
            statement.setString(1, cityName);
            resultSet = statement.executeQuery();
            createAirports(resultSet, airports, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airports by city", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return airports;
    }

    @Override
    public List<Airport> findAirportsByCountry(String countryName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORTS_BY_COUNTRY);
            statement.setString(1, countryName);
            resultSet = statement.executeQuery();
            createAirports(resultSet, airports, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airports by country", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return airports;
    }

    private void createAirports(ResultSet resultSet, List<Airport> airports, EntityFactory factory) throws SQLException {
        while (resultSet.next()) {
            Map<String, Object> airportData = createAirportData(resultSet);
            Airport airport = factory.getAirportCreator().create(airportData);
            logger.log(Level.DEBUG, "Airport found: {}", airport);
            airports.add(airport);
        }
    }

    @Override
    public boolean addAirport(Airport airport) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_AIRPORT);
            statement.setString(1, airport.getAirportName());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did airport add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while adding airport", e);
        } finally {
            closeStatement(statement);
        }
        return isAdded;
    }

    @Override
    public boolean updateAirport(Airport airport) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_AIRPORT);
            statement.setString(1, airport.getAirportName());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            statement.setInt(4, airport.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did airport update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating airport", e);
        } finally {
            closeStatement(statement);
        }
        return isUpdated;
    }
}
