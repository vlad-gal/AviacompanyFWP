package by.halatsevich.company.service.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.dao.DaoFactory;
import by.halatsevich.company.dao.UserDao;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.service.UserService;
import by.halatsevich.company.service.exception.ServiceException;
import by.halatsevich.company.service.validator.UserValidator;
import by.halatsevich.company.service.util.PasswordEncryption;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public User authorization(AuthorizationData authorizationData) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        String login = authorizationData.getLogin();
        String password = PasswordEncryption.encryptPassword(authorizationData.getPassword());
        User user;
        try {
            Optional<User> userData = userDao.selectUserByLogin(authorizationData.getLogin());
            String foundLogin = userData.get().getLogin();
            String foundPassword = userData.get().getPassword();
            if (UserValidator.isValidUserLoginPass(login, password, foundLogin, foundPassword)) {
                user = userData.get();
            } else {
                throw new ServiceException("Incorrect login or password");
            }
        } catch (DaoException e) {
            String exceptionMessage = String.format("User with login: %s - not found", login);
            throw new ServiceException(exceptionMessage, e);
        }

        return user;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws ServiceException {
        return false;
    }

    @Override
    public List<User> selectAllUsers() throws ServiceException {
        return null;
    }


}
