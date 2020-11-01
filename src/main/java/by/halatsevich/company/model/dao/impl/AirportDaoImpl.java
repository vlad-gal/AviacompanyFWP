package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.AirportDao;
import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AirportDaoImpl implements AirportDao {

    @Override
    public List<Airport> findAll() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<Airport> airports = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRPORTS);
            resultSet = statement.executeQuery();
            createAirports(resultSet, airports, factory);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all airports", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return airports;
    }

    private Map<String, Object> createAirportData(ResultSet resultSet) throws SQLException {
        Map<String, Object> airportData = new HashMap<>();
        airportData.put(ColumnName.AIRPORT_ID, resultSet.getInt(1));
        airportData.put(ColumnName.AIRPORT_NAME, resultSet.getString(2));
        airportData.put(ColumnName.COUNTRY, resultSet.getString(3));
        airportData.put(ColumnName.CITY, resultSet.getString(4));
        return airportData;
    }

    @Override
    public Optional<Airport> findById(int airportId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
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
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(airport);
    }

    @Override
    public Optional<Airport> findAirportByName(String airportName) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
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
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(airport);
    }

    @Override
    public List<Airport> findAirportsByCity(String cityName) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
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
            closeStatement(statement);
            closeConnection(connection);
        }
        return airports;
    }

    @Override
    public List<Airport> findAirportsByCountry(String countryName) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
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
            closeStatement(statement);
            closeConnection(connection);
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
        Connection connection = ConnectionPool.INSTANCE.getConnection();
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
            closeConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean update(Airport airport) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
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
            closeConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean remove(int airportId) throws DaoException { // TODO: 20.10.2020 test method
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_AIRPORT_BY_ID);
            statement.setInt(1, airportId);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isRemoved = true;
            }
            logger.log(Level.DEBUG, "Did airport remove? {}", isRemoved);
        } catch (SQLException e) {
            throw new DaoException("Error while removing airport", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isRemoved;
    }
}
