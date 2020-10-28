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
    boolean isUserExist(String login, String email) throws ServiceException;
    boolean isEmailExist(String email) throws ServiceException;

    boolean updateUserStatus(String email, Status active) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    boolean updatePassword(User user, String password) throws ServiceException;



}
