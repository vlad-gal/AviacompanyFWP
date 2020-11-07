package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    String findPasswordByLogin(String login) throws DaoException;

    boolean registration(RegistrationData registrationData) throws DaoException;

    boolean updatePassword(String login, String password) throws DaoException;
}
