package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao {

    List<User> findAllUsers() throws DaoException;

    Optional<User> authorization(AuthorizationData authorizationData) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(int userId) throws DaoException;

    boolean registration(RegistrationData registrationData) throws DaoException;

    boolean removeUser(int userId) throws DaoException;

    boolean updateUser(User user) throws DaoException;
}
