package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User authorization(AuthorizationData authorizationData) throws ServiceException;
    boolean registration(RegistrationData registrationData) throws ServiceException;

//    User updateUser();


    List<User> findAllUsers() throws ServiceException;

// updateUserPassword
    //
//
//    boolean createStatus();
//    boolean updateStatus();
//    boolean removeStatus();
//    boolean selectStatus();
//
//    boolean createRole();
//    boolean updateRole();
//    boolean removeRole();
//    boolean selectRole();


}
