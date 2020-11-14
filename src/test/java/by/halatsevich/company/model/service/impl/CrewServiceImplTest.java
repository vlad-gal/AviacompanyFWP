package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.CrewDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.UserDao;
import by.halatsevich.company.model.dao.impl.CrewDaoImpl;
import by.halatsevich.company.model.dao.impl.UserDaoImpl;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
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
public class CrewServiceImplTest {
    DaoFactory daoFactory;
    UserDao userDao;
    CrewDao crewDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        crewDao = Mockito.mock(CrewDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
        Mockito.when(daoFactory.getCrewDao()).thenReturn(crewDao);
    }

    @Test
    public void testAddCrewSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        try {
            Mockito.when(crewDao.addCrew(Mockito.any(CrewDto.class))).thenReturn(true);
            boolean condition = service.addCrew(new User(), "crewName", "2", "3",
                    "4", "1", Status.ACTIVE);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddCrewException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(crewDao.addCrew(Mockito.any(CrewDto.class))).thenThrow(DaoException.class);
        service.addCrew(new User(), "crewName", "2", "3",
                "4", "1", Status.ACTIVE);
    }

    @Test
    public void testAddUserIntoCrewStatus() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        try {
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            Mockito.when(crewDao.addUserIntoCrew(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
            Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(true);
            boolean condition = service.addUserIntoCrew(new User(), "1");
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddUserIntoCrewException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
        Mockito.when(crewDao.addUserIntoCrew(Mockito.anyInt(), Mockito.anyInt())).thenThrow(DaoException.class);
        service.addUserIntoCrew(new User(), "1");
    }

    @Test
    public void testFindCrewsByStatusSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        List<CrewDto> crewDtoList = new ArrayList<>();
        crewDtoList.add(new CrewDto());
        try {
            Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            Mockito.when(crewDao.findAllByStatus(Mockito.any(Status.class))).thenReturn(crewDtoList);
            List<Crew> actual = service.findCrewsByStatus("active");
            assertEquals(actual.size(), crewDtoList.size());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindCrewsByStatusException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
        Mockito.when(crewDao.findAllByStatus(Mockito.any(Status.class))).thenThrow(DaoException.class);
        service.findCrewsByStatus("active");
    }

    @Test
    public void testFindCrewByIdSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Crew crew = new Crew();
        crew.setStaff(new ArrayList<>());
        try {
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            Crew actual = service.findCrewById(1);
            assertEquals(actual, crew);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindCrewByIdException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(crewDao.findById(Mockito.anyInt())).thenThrow(DaoException.class);
        service.findCrewById(1);
    }

    @Test
    public void testFindCrewByNameSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        CrewDto crewDto = new CrewDto();
        try {
            Mockito.when(crewDao.findByCrewName(Mockito.anyString())).thenReturn(Optional.of(new CrewDto()));
            Optional<CrewDto> actual = service.findByCrewName("Crew");
            assertEquals(actual, Optional.of(crewDto));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindCrewByNameException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(crewDao.findByCrewName(Mockito.anyString())).thenThrow(DaoException.class);
        service.findByCrewName("Crew");
    }

    @Test
    public void testFindUserCrewsByStatusSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        List<Crew> crews = new ArrayList<>();
        crews.add(new Crew());
        List<CrewDto> crewsDto = new ArrayList<>();
        crewsDto.add(new CrewDto());
        try {
            Mockito.when(crewDao.findUserCrewsByStatus(Mockito.anyInt(), Mockito.any(Status.class))).thenReturn(crewsDto);
            List<Crew> actual = service.findUserCrewsByStatus(new User(), "active");
            assertEquals(actual.size(), crews.size());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUserCrewsByStatusException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        Mockito.when(crewDao.findUserCrewsByStatus(Mockito.anyInt(), Mockito.any(Status.class))).thenThrow(DaoException.class);
        service.findUserCrewsByStatus(new User(), "active");
    }

    @Test
    public void testCountAvailablePlacesInCrewSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        List<User.Role> roles = new ArrayList<>();
        roles.add(User.Role.PILOT);
        User pilot = new User();
        pilot.setRole(User.Role.PILOT);
        Crew crew = new Crew();
        crew.setNumberOfPilots(2);
        try {
            Mockito.when(crewDao.findUserRolesByCrewId(Mockito.anyInt())).thenReturn(roles);
            int actual = service.countAvailablePlacesInCrew(crew, pilot);
            assertEquals(actual, roles.size());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testCountAvailablePlacesInCrewException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        User pilot = new User();
        pilot.setRole(User.Role.PILOT);
        Crew crew = new Crew();
        crew.setNumberOfPilots(2);
        Mockito.when(crewDao.findUserRolesByCrewId(Mockito.anyInt())).thenThrow(DaoException.class);
        service.countAvailablePlacesInCrew(crew, pilot);
    }

    @Test
    public void testUpdateCrewSuccess() {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        List<User> users = new ArrayList<>();
        Crew crew = new Crew(new User(), "asd", users, 1, 2,
                3, 5, Status.ACTIVE);
        try {
            Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(true);
            Mockito.when(crewDao.update(Mockito.any(CrewDto.class))).thenReturn(true);
            boolean condition = service.updateCrew(crew, "1", "2", "3",
                    "2", "active");
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateCrewException() throws DaoException, ServiceException {
        CrewService service = ServiceFactory.getInstance().getCrewService();
        List<User> users = new ArrayList<>();
        Crew crew = new Crew(new User(), "asd", users, 1, 2,
                3, 5, Status.ACTIVE);
        Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(crewDao.update(Mockito.any(CrewDto.class))).thenThrow(DaoException.class);
        service.updateCrew(crew, "1", "2", "3",
                "2", "active");
    }
}