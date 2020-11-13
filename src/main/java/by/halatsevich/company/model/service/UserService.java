package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents user service.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface UserService {

    /**
     * Authorization user.
     *
     * @param authorizationData the authorization data
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> authorization(AuthorizationData authorizationData) throws ServiceException;

    /**
     * Find users by role and status.
     *
     * @param role   the role
     * @param status the status
     * @return the list of users
     * @throws ServiceException the service exception
     */
    List<User> findUsersByRoleAndStatus(String role, String status) throws ServiceException;

    /**
     * Find users by status.
     *
     * @param status the status
     * @return the list of users
     * @throws ServiceException the service exception
     */
    List<User> findUsersByStatus(String status) throws ServiceException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional of user
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByLogin(String login) throws ServiceException;

    /**
     * Registration user.
     *
     * @param registrationData the registration data
     * @return true if registration successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean registration(RegistrationData registrationData) throws ServiceException;

    /**
     * Update password.
     *
     * @param user     the user
     * @param password the password
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updatePassword(User user, String password) throws ServiceException;

    /**
     * Update user.
     *
     * @param user the user
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateUser(User user) throws ServiceException;
}
