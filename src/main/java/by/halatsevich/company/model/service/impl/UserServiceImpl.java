package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.UserDao;
import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.util.PasswordEncryption;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> authorization(AuthorizationData authorizationData) throws ServiceException {
        String login = authorizationData.getLogin();
        String password = authorizationData.getPassword();
        String hashingPassword = PasswordEncryption.encryptPassword(password);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Optional<User> user;
        try {
            String foundPassword = userDao.findPasswordByLogin(login);
            if (foundPassword != null && !foundPassword.isEmpty() && hashingPassword.equals(foundPassword)) {
                user = userDao.findUserByLogin(login);
            } else {
                user = Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while authorization user", e);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        List<User> users;
        try {
            users = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all users", e);
        }
        return users;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserRegistered;
        try {
            String encryptedPassword = PasswordEncryption.encryptPassword(registrationData.getPassword());
            registrationData.setPassword(encryptedPassword);
            isUserRegistered = userDao.registration(registrationData);
        } catch (DaoException e) {
            throw new ServiceException("Error while registering user", e);
        }
        return isUserRegistered;
    }

    @Override
    public boolean updateUserStatus(User user, Status status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserUpdated;
        try {
            user.setStatus(status);
            isUserUpdated = userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating user status", e);
        }
        return isUserUpdated;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Optional<User> user;
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by email", e);
        }
        return user;
    }

    @Override
    public boolean updatePassword(User user, String password) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserUpdated;
        try {
            String encryptedPassword = PasswordEncryption.encryptPassword(password);
            isUserUpdated = userDao.updatePassword(user.getEmail(), encryptedPassword);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating password", e);
        }
        return isUserUpdated;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserUpdated;
        try {
            isUserUpdated = userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating user", e);
        }
        return isUserUpdated;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Optional<User> user;
        try {
            user = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by login", e);
        }
        return user;
    }
}
