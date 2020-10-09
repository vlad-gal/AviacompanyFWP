package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> selectAllUsers() throws DaoException;

    Optional<User> authorization(AuthorizationData authorizationData) throws DaoException;

    Optional<User> selectUserByLogin(String login) throws DaoException;

    boolean registration(RegistrationData registrationData) throws DaoException;

    boolean removeUser(int userId) throws DaoException;

    Optional<User> updateUser(User user) throws DaoException;
}
