package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao {

    List<User> findAllUsers() throws DaoException;

    Optional<User> authorization(AuthorizationData authorizationData) throws DaoException;

    Optional<User> selectUserByLogin(String login) throws DaoException;

    Optional<User> selectUserById(int userId) throws DaoException;

    boolean registration(RegistrationData registrationData) throws DaoException;

    boolean removeUser(int userId) throws DaoException;

    boolean updateUser(User user) throws DaoException;
}
