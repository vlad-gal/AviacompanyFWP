package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.impl.AircraftDaoImpl;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
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
public class AircraftServiceImplTest {
    DaoFactory daoFactory;
    AircraftDao aircraftDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        aircraftDao = Mockito.mock(AircraftDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getAircraftDao()).thenReturn(aircraftDao);
    }

    @Test
    public void testAddAircraftSuccess() {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        try {
            Mockito.when(aircraftDao.addAircraft(Mockito.any(Aircraft.class))).thenReturn(true);
            boolean condition = service.addAircraft("1", "2", "cargo", Status.ACTIVE);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddAircraftException() throws DaoException, ServiceException {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Mockito.when(aircraftDao.addAircraft(Mockito.any(Aircraft.class))).thenThrow(DaoException.class);
        service.addAircraft("1", "2", "cargo", Status.ACTIVE);
    }

    @Test
    public void testFindAllAircraftsSuccess() {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(new Aircraft());
        try {
            Mockito.when(aircraftDao.findAll()).thenReturn(aircrafts);
            List<Aircraft> actual = service.findAllAircrafts();
            assertEquals(actual, aircrafts);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindAllAircraftsException() throws DaoException, ServiceException {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Mockito.when(aircraftDao.findAll()).thenThrow(DaoException.class);
        service.findAllAircrafts();
    }

    @Test
    public void testFindAircraftByIdSuccess() {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Aircraft aircraft = new Aircraft();
        try {
            Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenReturn(Optional.of(aircraft));
            Optional<Aircraft> actual = service.findAircraftById("1");
            assertEquals(actual, Optional.of(aircraft));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindAircraftByIdException() throws DaoException, ServiceException {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenThrow(DaoException.class);
        service.findAircraftById("1");
    }

    @Test
    public void testFindAircraftsByStatusSuccess() {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(new Aircraft());
        try {
            Mockito.when(aircraftDao.findAllByStatus(Mockito.any(Status.class))).thenReturn(aircrafts);
            List<Aircraft> actual = service.findAircraftsByStatus("active");
            assertEquals(actual, aircrafts);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindAircraftsByStatusException() throws DaoException, ServiceException {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Mockito.when(aircraftDao.findAllByStatus(Mockito.any(Status.class))).thenThrow(DaoException.class);
        service.findAircraftsByStatus("active");
    }

    @Test
    public void testUpdateAircraftSuccess() {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Aircraft aircraft = new Aircraft();
        try {
            Mockito.when(aircraftDao.update(Mockito.any(Aircraft.class))).thenReturn(true);
            boolean condition = service.updateAircraft(aircraft);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateAircraftException() throws DaoException, ServiceException {
        AircraftService service = ServiceFactory.getInstance().getAircraftService();
        Aircraft aircraft = new Aircraft();
        Mockito.when(aircraftDao.update(Mockito.any(Aircraft.class))).thenThrow(DaoException.class);
        service.updateAircraft(aircraft);
    }
}