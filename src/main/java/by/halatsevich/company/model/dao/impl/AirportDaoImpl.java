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
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRPORTS)) {
            ResultSet resultSet = statement.executeQuery();
            return createAirports(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all airports", e);
        }
    }

    @Override
    public Optional<Airport> findById(int airportId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORT_BY_ID)) {
            statement.setInt(1, airportId);
            ResultSet resultSet = statement.executeQuery();
            Airport airport = null;
            EntityFactory factory = EntityFactory.getInstance();
            while (resultSet.next()) {
                Map<String, Object> airportData = createAirportData(resultSet);
                airport = factory.getAirportCreator().create(airportData);
                logger.log(Level.DEBUG, "Airport found: {}", airport);
            }
            return Optional.ofNullable(airport);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airport by id", e);
        }
    }

    @Override
    public Optional<Airport> findAirportByName(String airportName) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORT_BY_NAME)) {
            statement.setString(1, airportName);
            ResultSet resultSet = statement.executeQuery();
            Airport airport = null;
            EntityFactory factory = EntityFactory.getInstance();
            while (resultSet.next()) {
                Map<String, Object> airportData = createAirportData(resultSet);
                airport = factory.getAirportCreator().create(airportData);
                logger.log(Level.DEBUG, "Airport found: {}", airport);
            }
            return Optional.ofNullable(airport);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airport by name", e);
        }
    }

    @Override
    public List<Airport> findAirportsByCity(String cityName) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORTS_BY_CITY)) {
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();
            return createAirports(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airports by city", e);
        }
    }

    @Override
    public List<Airport> findAirportsByCountry(String countryName) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_AIRPORTS_BY_COUNTRY)) {
            statement.setString(1, countryName);
            ResultSet resultSet = statement.executeQuery();
            return createAirports(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding airports by country", e);
        }
    }

    @Override
    public boolean addAirport(Airport airport) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_AIRPORT)) {
            statement.setString(1, airport.getAirportName());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while adding airport", e);
        }
    }

    @Override
    public boolean update(Airport airport) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_AIRPORT)) {
            statement.setString(1, airport.getAirportName());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            statement.setInt(4, airport.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating airport", e);
        }
    }

    @Override
    public boolean remove(int airportId) throws DaoException {
        throw new UnsupportedOperationException("Operation Remove not allowed with airport");
    }

    private Map<String, Object> createAirportData(ResultSet resultSet) throws SQLException {
        Map<String, Object> airportData = new HashMap<>();
        airportData.put(ColumnName.AIRPORT_ID, resultSet.getInt(1));
        airportData.put(ColumnName.AIRPORT_NAME, resultSet.getString(2));
        airportData.put(ColumnName.COUNTRY, resultSet.getString(3));
        airportData.put(ColumnName.CITY, resultSet.getString(4));
        return airportData;
    }

    private List<Airport> createAirports(ResultSet resultSet) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> airportData = createAirportData(resultSet);
            Airport airport = EntityFactory.getInstance().getAirportCreator().create(airportData);
            logger.log(Level.DEBUG, "Airport found: {}", airport);
            airports.add(airport);
        }
        return airports;
    }
}
