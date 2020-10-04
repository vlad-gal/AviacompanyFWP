package by.halatsevich.company.service;

import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User authorization(AuthorizationData authorizationData) throws ServiceException;
    boolean registration(RegistrationData registrationData) throws ServiceException;
    List<User> selectAllUsers() throws ServiceException;
}
