package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.UserDao;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.factory.EntityFactory;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl extends MySqlDao implements UserDao {
    // TODO: 08.10.2020 проверить работу всех методов
    @Override
    public List<User> selectAllUsers() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        EntityFactory<User> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                User user = factory.createEntity(userData);
                logger.log(Level.DEBUG, "User was found: {}", user);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while selecting all users", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return users;
    }

    @Override
    public Optional<User> authorization(AuthorizationData authorizationData) throws DaoException {
        String login = authorizationData.getLogin();
        return selectUserByLogin(login);
    }

    @Override
    public Optional<User> selectUserByLogin(String login) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        EntityFactory<User> factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.createEntity(userData);
                logger.log(Level.DEBUG, "User was found: {}", user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while select user by login", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(user);
    }

    private Map<String, Object> createUserData(ResultSet resultSet) throws SQLException {
        Map<String, Object> userData = new HashMap<>();
        userData.put(SqlColumnName.USER_ID, resultSet.getInt(1));
        userData.put(SqlColumnName.EMAIL, resultSet.getString(2));
        userData.put(SqlColumnName.PASSWORD, resultSet.getString(3));
        userData.put(SqlColumnName.STATUS_NAME, resultSet.getString(4).toUpperCase());
        userData.put(SqlColumnName.ROLE_NAME, resultSet.getString(5).toUpperCase());
        userData.put(SqlColumnName.FIRST_NAME, resultSet.getString(6));
        userData.put(SqlColumnName.LAST_NAME, resultSet.getString(7));
        userData.put(SqlColumnName.TELEPHONE_NUMBER, resultSet.getLong(8));
        userData.put(SqlColumnName.ENTITY_NAME, User.class.getSimpleName());
        return userData;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isAdded;
        try {
            int defaultRole = User.Role.DEFAULT.ordinal() + 1;
            int inactiveStatus = User.Status.INACTIVE.ordinal() + 1;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.INSERT_USER,new String[]{SqlColumnName.USER_ID});
            statement.setString(1, registrationData.getEmail());
            statement.setString(2, registrationData.getLogin());
            statement.setString(3, registrationData.getPassword());
            statement.setInt(4, defaultRole);
            statement.setInt(5, inactiveStatus);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
//            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN);
//            statement.setString(1, registrationData.getLogin());
//            resultSet = statement.executeQuery();
            int userId = 0;
            while (resultSet.next()) {
                userId = resultSet.getInt(SqlColumnName.USER_ID);
            }
            statement = connection.prepareStatement(SqlQuery.INSERT_PERSONAL_DATA);
            statement.setInt(1, userId);
            statement.setString(2, registrationData.getFirstName());
            statement.setString(3, registrationData.getLastName());
            statement.setLong(4, registrationData.getTelephoneNumber());
            statement.executeUpdate();
            connection.commit();
            isAdded = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DaoException("Cannot rollback transaction", ex);
            }
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Cannot set auto commit", e);
            }
        }
        return isAdded;
    }

    @Override
    public boolean removeUser(int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_USER_BY_ID);
            statement.setInt(1, userId);
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


    @Override
    public Optional<User> updateUser(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            int role = user.getRole().ordinal() + 1;
            int status = user.getStatus().ordinal() + 1;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.UPDATE_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setInt(4, role);
            statement.setInt(5, status);
            statement.setString(5, user.getLogin());
            statement.executeUpdate();
            statement = connection.prepareStatement(SqlQuery.UPDATE_PERSONAL_DATA);
            statement.setString(1, user.getUserData().getFirstName());
            statement.setString(2, user.getUserData().getLastName());
            statement.setLong(3, user.getUserData().getTelephoneNumber());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DaoException("Cannot rollback transaction", ex);
            }
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeStatement(statement);
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Cannot set auto commit", e);
            }
        }
        return Optional.ofNullable(user);
    }
}
