package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.impl.UserDaoImpl;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.util.PasswordEncryption;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@PrepareForTest(DaoFactory.class)
public class UserServiceImplTest {
    DaoFactory daoFactory;
    UserDaoImpl userDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
    }

    @Test
    public void testAuthorizationSuccessful() {
        UserService service = ServiceFactory.getInstance().getUserService();
        AuthorizationData authorizationData = new AuthorizationData("vladhala", "password");
        User user = new User();
        user.setLogin("vladhala");
        try {
            Mockito.when(userDao.findPasswordByLogin(authorizationData.getLogin()))
                    .thenReturn("c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b");
            Mockito.when(userDao.findUserByLogin(authorizationData.getLogin())).thenReturn(Optional.of(user));
            Optional<User> actual = service.authorization(authorizationData);
            assertEquals(actual, Optional.of(user));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAuthorizationFailure() {
        UserService service = ServiceFactory.getInstance().getUserService();
        AuthorizationData authorizationData = new AuthorizationData("vladhala", "password");
        User user = new User();
        user.setLogin("vladhala");
        try {
            Mockito.when(userDao.findPasswordByLogin(authorizationData.getLogin()))
                    .thenReturn("password");
            Mockito.when(userDao.findUserByLogin(authorizationData.getLogin())).thenReturn(Optional.empty());
            Optional<User> actual = service.authorization(authorizationData);
            assertNotEquals(actual, Optional.of(user));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAuthorizationException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        AuthorizationData authorizationData = new AuthorizationData("", "");
        Mockito.when(userDao.findPasswordByLogin(Mockito.anyString())).thenThrow(DaoException.class);
        service.authorization(authorizationData);
    }

    @Test
    public void testFindUsersByStatusSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setLogin("vladhala");
        user1.setStatus(Status.ACTIVE);
        User user2 = new User();
        user2.setLogin("novikmish");
        user2.setStatus(Status.ACTIVE);
        users.add(user1);
        users.add(user2);
        try {
            Mockito.when(userDao.findAllByStatus(Status.ACTIVE)).thenReturn(users);
            List<User> actual = service.findUsersByStatus(Status.ACTIVE.getStatusName());
            assertEquals(actual, users);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUsersByStatusException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.findAllByStatus(Status.ACTIVE)).thenThrow(DaoException.class);
        service.findUsersByStatus(Status.ACTIVE.getStatusName());
    }

    @Test
    public void testFindUsersByRoleAndStatusSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setLogin("vladhala");
        user1.setRole(User.Role.ADMIN);
        user1.setStatus(Status.ACTIVE);
        User user2 = new User();
        user2.setLogin("novikmish");
        user2.setRole(User.Role.ADMIN);
        user2.setStatus(Status.ACTIVE);
        users.add(user1);
        users.add(user2);
        try {
            Mockito.when(userDao.findUsersByRoleAndStatus(User.Role.ADMIN, Status.ACTIVE)).thenReturn(users);
            List<User> actual = service.findUsersByRoleAndStatus(User.Role.ADMIN.getRoleName(), Status.ACTIVE.getStatusName());
            assertEquals(actual, users);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUsersByRoleAndStatusException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.findUsersByRoleAndStatus(User.Role.ADMIN, Status.ACTIVE)).thenThrow(DaoException.class);
        service.findUsersByRoleAndStatus(User.Role.ADMIN.getRoleName(), Status.ACTIVE.getStatusName());
    }

    @Test
    public void testFindUserByLoginSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = new User();
        user.setLogin("vladhala");
        try {
            Mockito.when(userDao.findUserByLogin(user.getLogin())).thenReturn(Optional.of(user));
            Optional<User> actual = service.findUserByLogin(user.getLogin());
            assertEquals(actual, Optional.of(user));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUserByLoginException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.findUserByLogin(Mockito.any())).thenThrow(DaoException.class);
        service.findUserByLogin("");
    }

    @Test
    public void testFindUserByEmailSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = new User();
        user.setEmail("vladhala@mail.ru");
        try {
            Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
            Optional<User> actual = service.findUserByEmail(user.getEmail());
            assertEquals(actual, Optional.of(user));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUserByEmailException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.findUserByEmail(Mockito.any())).thenThrow(DaoException.class);
        service.findUserByEmail("");
    }

    @Test
    public void testRegistrationSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        RegistrationData registrationData = new RegistrationData("vladhala", "vlad@gmai.com",
                "passwod", "vlad", "gala", "123123123123", "admin", "busy");
        try {
            Mockito.when(userDao.registration(registrationData)).thenReturn(true);
            boolean condition = service.registration(registrationData);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRegistrationException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        RegistrationData registrationData = new RegistrationData("vladhala", "vlad@gmai.com",
                "passwod", "vlad", "gala", "123123123123", "admin", "busy");
        Mockito.when(userDao.registration(Mockito.any())).thenThrow(DaoException.class);
        service.registration(registrationData);
    }

    @Test
    public void testUpdatePasswordSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = new User();
        user.setEmail("hjasd@kasd.sd");
        String password = "asdqweqe";
        try {
            Mockito.when(userDao.updatePassword(user.getEmail(), PasswordEncryption.encryptPassword(password))).thenReturn(true);
            boolean condition = service.updatePassword(user, password);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdatePasswordException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.updatePassword(Mockito.any(), Mockito.anyString())).thenThrow(DaoException.class);
        service.updatePassword(new User(), "");
    }

    @Test
    public void testUpdateUserSuccess() {
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = new User();
        try {
            Mockito.when(userDao.update(user)).thenReturn(true);
            boolean condition = service.updateUser(user);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateUserException() throws DaoException, ServiceException {
        UserService service = ServiceFactory.getInstance().getUserService();
        Mockito.when(userDao.update(Mockito.any())).thenThrow(DaoException.class);
        service.updateUser(new User());
    }
}