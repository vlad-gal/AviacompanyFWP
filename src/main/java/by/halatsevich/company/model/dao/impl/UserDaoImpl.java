package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.dao.UserDao;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            EntityFactory factory = EntityFactory.getInstance();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                User user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        }
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        boolean isAdded;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.INSERT_USER, new String[]{ColumnName.USER_ID});
            statement.setString(1, registrationData.getEmail());
            statement.setString(2, registrationData.getLogin());
            statement.setString(3, registrationData.getPassword());
            statement.setInt(4, User.Role.valueOf(registrationData.getRole().toUpperCase()).ordinal());
            statement.setInt(5, Status.valueOf(registrationData.getStatus().toUpperCase()).ordinal());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            int userId = -1;
            while (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(SqlQuery.INSERT_PERSONAL_DATA);
            statement.setInt(1, userId);
            statement.setString(2, registrationData.getFirstName());
            statement.setString(3, registrationData.getLastName());
            statement.setLong(4, Long.parseLong(registrationData.getTelephoneNumber()));
            isAdded = statement.executeUpdate() > 0;
            logger.log(Level.DEBUG, "Did user add? {}", isAdded);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeStatement(statement);
            returnAutoCommit(connection);
            closeConnection(connection);
        }
        return isAdded;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            EntityFactory factory = EntityFactory.getInstance();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login", e);
        }
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String password = null;
            while (resultSet.next()) {
                password = resultSet.getString(1);
                logger.log(Level.DEBUG, "Password found");
            }
            return password;
        } catch (SQLException e) {
            throw new DaoException("Error while finding password by login", e);
        }
    }

    @Override
    public Optional<User> findById(int userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            EntityFactory factory = EntityFactory.getInstance();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by id", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        EntityFactory factory = EntityFactory.getInstance();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by email", e);
        }
    }

    @Override
    public boolean remove(int userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_USER_BY_ID)) {
            statement.setInt(1, Status.INACTIVE.ordinal());
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while deleting user", e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated;
        try {
            int role = user.getRole().ordinal();
            int status = user.getStatus().ordinal();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.UPDATE_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setInt(3, role);
            statement.setInt(4, status);
            statement.setString(5, user.getLogin());
            statement.executeUpdate();
            statement = connection.prepareStatement(SqlQuery.UPDATE_PERSONAL_DATA);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setLong(3, user.getTelephoneNumber());
            statement.setInt(4, user.getId());
            isUpdated = statement.executeUpdate() > 0;
            logger.log(Level.DEBUG, "Did user update? {}", isUpdated);
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            throw new DaoException("Error while updating user", e);
        } finally {
            closeStatement(statement);
            returnAutoCommit(connection);
            closeConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean updatePassword(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating user", e);
        }
    }

    private Map<String, Object> createUserData(ResultSet resultSet) throws SQLException {
        Map<String, Object> userData = new HashMap<>();
        userData.put(ColumnName.USER_ID, resultSet.getInt(1));
        userData.put(ColumnName.EMAIL, resultSet.getString(2));
        userData.put(ColumnName.LOGIN, resultSet.getString(3));
        userData.put(ColumnName.STATUS_NAME, resultSet.getString(4).toUpperCase());
        userData.put(ColumnName.ROLE_NAME, resultSet.getString(5).toUpperCase());
        userData.put(ColumnName.FIRST_NAME, resultSet.getString(6));
        userData.put(ColumnName.LAST_NAME, resultSet.getString(7));
        userData.put(ColumnName.TELEPHONE_NUMBER, resultSet.getLong(8));
        return userData;
    }
}