package by.halatsevich.company.dao.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.UserDao;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.entity.UserData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String, String> findPasswordByLogin(String login) throws DaoException {
        ConnectionPool instance = ConnectionPool.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> userData = new HashMap<>();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userData.put(ParameterName.LOGIN, resultSet.getString(1));
                userData.put(ParameterName.PASSWORD, resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DaoException("Erroe while finding password by login", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return userData;
    }

    @Override
    public User authorization(AuthorizationData authorizationData) throws DaoException {
        ConnectionPool instance = ConnectionPool.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN);
            statement.setString(1, authorizationData.getLogin());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String email = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                long telephoneNumber = resultSet.getLong(5);
                User.Role role = User.Role.valueOf(resultSet.getString(6).toUpperCase());
                UserData data = new UserData(firstName, lastName, telephoneNumber);
                User.Status status = User.Status.valueOf(resultSet.getString(7).toUpperCase());
                user = new User(email, login, role, status, data);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while authorization user", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return user;
    }

    @Override
    public List<User> selectAll() throws DaoException {
        return null;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws DaoException {
        return false;
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
