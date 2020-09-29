package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.UserDao;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User authorization(AuthorizationData authorizationData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws DaoException {
        return false;
    }





    @Override
    public List<User> select() throws DaoException {
        return null;
    }

    @Override
    public boolean add() throws DaoException {
        return false;
    }

    @Override
    public User update() throws DaoException {
        return null;
    }

    @Override
    public boolean delete() throws DaoException {
        return false;
    }
}
