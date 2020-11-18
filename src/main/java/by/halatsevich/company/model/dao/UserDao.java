package by.halatsevich.company.model.dao;

import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents user dao. Extends the interface BaseDao, defines specific methods
 * which interactions with User entities in database.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find users by role and status.
     *
     * @param role   the role
     * @param status the status
     * @return the list of users
     * @throws DaoException the dao exception
     */
    List<User> findUsersByRoleAndStatus(User.Role role, Status status) throws DaoException;

    /**
     * Find password by login.
     *
     * @param login the login
     * @return the password
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Registration user.
     *
     * @param registrationData the registration data
     * @return true if registration successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean registration(RegistrationData registrationData) throws DaoException;

    /**
     * Update password.
     *
     * @param email    the email
     * @param password the password
     * @return true if updating successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean updatePassword(String email, String password) throws DaoException;
}
