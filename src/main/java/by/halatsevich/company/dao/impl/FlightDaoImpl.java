package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.FlightDao;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.Aircraft;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Aircraft> selectAllAircraft() throws DaoException {
        ConnectionPool instance = ConnectionPool.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aircraft> aircrafts = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_AIRCRAFT);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tailNumber = resultSet.getString(1);
                String aircraftName = resultSet.getString(2);
                String aircraftType = resultSet.getString(3);
                Aircraft.AircraftType aircraftType1 = Aircraft.AircraftType.valueOf(aircraftType.toUpperCase());
                Aircraft aircraft = new Aircraft(tailNumber, aircraftName, aircraftType1);
                aircrafts.add(aircraft);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return aircrafts;
    }

    private void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing statement", e);
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing ResultSet", e);
        }
    }
}
