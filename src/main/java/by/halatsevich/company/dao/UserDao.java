package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User authorization(AuthorizationData authorizationData) throws DaoException;

    boolean registration(RegistrationData registrationData) throws DaoException;
    List<User> selectAll() throws DaoException;

    Map<String,String> findPasswordByLogin(String login) throws DaoException;
}
