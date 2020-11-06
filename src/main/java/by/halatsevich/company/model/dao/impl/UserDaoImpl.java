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
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        List<User> users = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                User user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public boolean registration(RegistrationData registrationData, User.Role role) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        boolean isAdded = false;
        try {
            int inactiveStatus = Status.INACTIVE.ordinal() + 1;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.INSERT_USER, new String[]{ColumnName.USER_ID});
            statement.setString(1, registrationData.getEmail());
            statement.setString(2, registrationData.getLogin());
            statement.setString(3, registrationData.getPassword());
            statement.setInt(4, role.ordinal()+1);// todo переделать таблицу начинать индексацию с 0
            statement.setInt(5, inactiveStatus);
            int firstAdd = statement.executeUpdate();
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
            int secondAdd = statement.executeUpdate();
            if (firstAdd + secondAdd == 2) {
                isAdded = true;
                connection.commit();
            } else {
                rollbackConnection(connection);
            }
            logger.log(Level.DEBUG, "Did user add? {}", isAdded);
        } catch (SQLException e) {
            rollbackConnection(connection);
            returnAutoCommit(connection);
            throw new DaoException("Error while inserting user", e);
        } finally {
            closeStatement(statement);
            returnAutoCommit(connection);
            closeConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean registrationUserByAdmin(RegistrationData registrationData, String role) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        boolean isAdded = false;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.INSERT_USER, new String[]{ColumnName.USER_ID});
            statement.setString(1, registrationData.getEmail());
            statement.setString(2, registrationData.getLogin());
            statement.setString(3, registrationData.getPassword());
            statement.setInt(4, User.Role.valueOf(role.toUpperCase()).ordinal() + 1);
            statement.setInt(5, Status.ACTIVE.ordinal() + 1);
            int firstAdd = statement.executeUpdate();
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
            int secondAdd = statement.executeUpdate();
            if (firstAdd + secondAdd == 2) {
                isAdded = true;
                connection.commit();
            } else {
                rollbackConnection(connection);
            }
            logger.log(Level.DEBUG, "Did user add? {}", isAdded);
        } catch (SQLException e) {
            rollbackConnection(connection);
            returnAutoCommit(connection);
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
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean findEmail(String email) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        boolean isFound = false;
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                isFound = true;
            }
            logger.log(Level.DEBUG, "Did email find? {}", isFound);
        } catch (SQLException e) {
            throw new DaoException("Error while finding email", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isFound;
    }

    @Override
    public boolean findLogin(String login) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        boolean isFound = false;
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                isFound = true;
            }
            logger.log(Level.DEBUG, "Did login find? {}", isFound);
        } catch (SQLException e) {
            throw new DaoException("Error while finding login", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isFound;
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException { // TODO: 20.10.2020 test method
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        String password = null;
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString(1);
                logger.log(Level.DEBUG, "Password found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding password by login", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return password;
    }

    @Override
    public Optional<User> findById(int userId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by id", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(user);
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

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> userData = createUserData(resultSet);
                user = factory.getUserCreator().create(userData);
                logger.log(Level.DEBUG, "User found: {}", user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by email", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean remove(int userId) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_USER_BY_ID);
            statement.setInt(1, userId);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isRemoved = true;
            }
            logger.log(Level.DEBUG, "Did user remove? {}", isRemoved);
        } catch (SQLException e) {
            throw new DaoException("Error while deleting user", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isRemoved;
    }

    @Override
    public boolean update(User user) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            int role = user.getRole().ordinal() + 1;
            int status = user.getStatus().ordinal() + 1;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SqlQuery.UPDATE_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setInt(3, role);
            statement.setInt(4, status);
            statement.setString(5, user.getLogin());
            int firstUpdate = statement.executeUpdate();
            statement = connection.prepareStatement(SqlQuery.UPDATE_PERSONAL_DATA);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setLong(3, user.getTelephoneNumber());
            statement.setInt(4, user.getId());
            int secondUpdate = statement.executeUpdate();
            if (firstUpdate + secondUpdate == 2) {
                isUpdated = true;
                connection.commit();
            } else {
                rollbackConnection(connection);//todo выкинуть
            }
            logger.log(Level.DEBUG, "Did user update? {}", isUpdated);
        } catch (SQLException e) {
            rollbackConnection(connection);
            returnAutoCommit(connection);
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
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD);
            statement.setString(1, password);
            statement.setString(2, login);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did user update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating user", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return isUpdated;
    }
}
