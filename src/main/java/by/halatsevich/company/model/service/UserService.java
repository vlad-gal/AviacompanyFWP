package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> authorization(AuthorizationData authorizationData) throws ServiceException;

    boolean registration(RegistrationData registrationData) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    Optional<User> findUserByLogin(String login) throws ServiceException;

    boolean updateUserStatus(User user, Status active) throws ServiceException;

    boolean updatePassword(User user, String password) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    List<User> findUsersByStatus(String status) throws ServiceException;

    List<User> findUsersByRoleAndStatus(User.Role role, Status status) throws ServiceException;
}
