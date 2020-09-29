package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

public interface UserDao extends BaseDao<User> {
    User authorization(AuthorizationData authorizationData) throws DaoException;
    boolean registration(RegistrationData registrationData) throws DaoException;
}
