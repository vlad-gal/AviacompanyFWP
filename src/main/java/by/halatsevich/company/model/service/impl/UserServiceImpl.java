package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.UserDao;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.model.service.exception.ServiceException;
import by.halatsevich.company.model.service.util.PasswordEncryption;
import by.halatsevich.company.model.service.validator.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public User authorization(AuthorizationData authorizationData) throws ServiceException {
        String login = authorizationData.getLogin();
        String password = authorizationData.getPassword();
        if (!UserValidator.isValidAuthorizationData(authorizationData)){
            throw new ServiceException("Authorization data is invalid");
        }
        String hashingPassword = PasswordEncryption.encryptPassword(password);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user;
        try {
            Optional<User> optionalUser = userDao.findUserByLogin(login);
            boolean isUserPresent = optionalUser.isPresent();
            if (!isUserPresent) {
                throw new ServiceException("User not found");
            }
            user = optionalUser.get();
            String foundPassword = user.getPassword();
            if (!hashingPassword.equals(foundPassword)) {
                throw new ServiceException("Passwords don't match");
            }
        } catch (DaoException e) {
            throw new ServiceException(String.format("User with login: %s - not found", login), e);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> users;
        try {
            users = dao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
        if (users.isEmpty()) {

        }
        return users;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
//        userDao.findUserByLogin();//check
//        userDao.findUserByEmail();
//        userDao.registration();
        return false;
    }
}
