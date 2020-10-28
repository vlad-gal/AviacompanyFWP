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
import by.halatsevich.company.validator.UserValidator;

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
            if (foundPassword == null || foundPassword.isEmpty() || !hashingPassword.equals(foundPassword)) {
                throw new ServiceException("Passwords don't match");
            }
            user = userDao.findUserByLogin(login);
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
            users = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all users", e);
        }
        return users;
    }

    @Override
    public boolean registration(RegistrationData registrationData) throws ServiceException {
        if (!UserValidator.isValidRegistrationData(registrationData)){
            throw new ServiceException("Invalid registration data");
        }
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
    public boolean isUserExist(String login, String email) throws ServiceException {
        if (!UserValidator.isValidLogin(login) || !UserValidator.isValidEmail(email)){
            throw new ServiceException("Invalid login or email");
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserExist = false;
        try {
            if (userDao.findLogin(login) || userDao.findEmail(email)){
                isUserExist = true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding exist user", e);
        }
        return isUserExist;
    }

    @Override
    public boolean isEmailExist(String email) throws ServiceException{
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isEmailExist;
        try {
            isEmailExist = userDao.findEmail(email);
        } catch (DaoException e){
            throw new ServiceException("Error while finding email",e);
        }
        return isEmailExist;
    }


    @Override
    public boolean updateUserStatus(String email, Status status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
        boolean isUserUpdated = false;
        try {
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                user.setStatus(status);
                isUserUpdated = userDao.update(user);
            }
        } catch (DaoException e){
            throw new ServiceException("Error while finding email",e);
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
            throw new ServiceException(String.format("User with email: %s - not found", email), e);
        }
        return user;
    }

    @Override
    public boolean updatePassword(User user, String password) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.getUserDao();
//        if (!UserValidator.isValidEmail(email)){
//            throw new ServiceException("Invalid email");
//        }
        boolean isUserUpdated = false;
//        try {
//            Optional<User> optionalUser = userDao.findUserByEmail(email);
//            if (optionalUser.isPresent()){
//                User user = optionalUser.get();
//                user.setStatus(status);
//                isUserUpdated = userDao.update(user);
//            } else {
//                throw new ServiceException("User not found");
//            }
//        } catch (DaoException e){
//            throw new ServiceException("Error while finding email",e);
//        }
        return isUserUpdated;
    }
}
