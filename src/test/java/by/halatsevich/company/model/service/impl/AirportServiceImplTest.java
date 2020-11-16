package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.AirportDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.impl.AirportDaoImpl;
import by.halatsevich.company.entity.Airport;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
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
public class AirportServiceImplTest {
    DaoFactory daoFactory;
    AirportDao airportDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        airportDao = Mockito.mock(AirportDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getAirportDao()).thenReturn(airportDao);
    }

    @Test
    public void testAddAirportSuccess() {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        try {
            Mockito.when(airportDao.addAirport(Mockito.any(Airport.class))).thenReturn(true);
            boolean condition = service.addAirport(new Airport());
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddAirportException() throws DaoException, ServiceException {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        Mockito.when(airportDao.addAirport(Mockito.any(Airport.class))).thenThrow(DaoException.class);
        service.addAirport(new Airport());
    }

    @Test
    public void testFindAllAirportsSuccess() {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport());
        try {
            Mockito.when(airportDao.findAll()).thenReturn(airports);
            List<Airport> actual = service.findAllAirports();
            assertEquals(actual, airports);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindAllAirportsException() throws DaoException, ServiceException {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        Mockito.when(airportDao.findAll()).thenThrow(DaoException.class);
        service.findAllAirports();
    }

    @Test
    public void testFindAirportByIdSuccess() {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        Airport airport = new Airport();
        try {
            Mockito.when(airportDao.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(airport));
            Optional<Airport> actual = service.findAirportById("1");
            assertEquals(actual, Optional.of(airport));
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindAirportByIdException() throws DaoException, ServiceException {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        Mockito.when(airportDao.findById(Mockito.anyInt())).thenThrow(DaoException.class);
        service.findAirportById("1");
    }

    @Test
    public void testUpdateAirportSuccess() {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        try {
            Mockito.when(airportDao.update(Mockito.any(Airport.class))).thenReturn(true);
            boolean condition = service.updateAirport(new Airport());
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateAirportException() throws DaoException, ServiceException {
        AirportService service = ServiceFactory.getInstance().getAirportService();
        Mockito.when(airportDao.update(Mockito.any(Airport.class))).thenThrow(DaoException.class);
        service.updateAirport(new Airport());
    }
}