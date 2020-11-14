package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.*;
import by.halatsevich.company.model.dao.impl.*;
import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@PrepareForTest(DaoFactory.class)
public class FlightServiceImplTest {
    DaoFactory daoFactory;
    FlightDao flightDao;
    UserDao userDao;
    AircraftDao aircraftDao;
    AirportDao airportDao;
    CrewDao crewDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        daoFactory = Mockito.mock(DaoFactory.class);
        flightDao = Mockito.mock(FlightDaoImpl.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        aircraftDao = Mockito.mock(AircraftDaoImpl.class);
        airportDao = Mockito.mock(AirportDaoImpl.class);
        crewDao = Mockito.mock(CrewDaoImpl.class);
        Mockito.when(DaoFactory.getInstance()).thenReturn(daoFactory);
        Mockito.when(daoFactory.getFlightDao()).thenReturn(flightDao);
        Mockito.when(daoFactory.getUserDao()).thenReturn(userDao);
        Mockito.when(daoFactory.getCrewDao()).thenReturn(crewDao);
        Mockito.when(daoFactory.getAircraftDao()).thenReturn(aircraftDao);
        Mockito.when(daoFactory.getAirportDao()).thenReturn(airportDao);
    }

    @Test
    public void testAddFlightSuccess() {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        try {
            Mockito.when(flightDao.addFlight(Mockito.any(FlightDto.class))).thenReturn(true);
            boolean condition = service.addFlight("1", "2", "2020-11-05T20:48",
                    "2020-11-09T20:48", "1", "2", 1);
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddFlightException() throws DaoException, ServiceException {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Mockito.when(flightDao.addFlight(Mockito.any(FlightDto.class))).thenThrow(DaoException.class);
        service.addFlight("1", "2", "2020-11-05T20:48",
                "2020-11-09T20:48", "1", "2", 1);
    }

    @Test
    public void testFindFlightsByStatusSuccess() {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        List<Flight> flights = new ArrayList<>();
        List<FlightDto> flightsDto = new ArrayList<>();
        flightsDto.add(new FlightDto(1, 2, 3333333333333L,
                3333333333333L, 5, 6, 7, Status.ACTIVE));
        flights.add(new Flight());
        try {
            Mockito.when(flightDao.findAllByStatus(Mockito.any(Status.class))).thenReturn(flightsDto);
            Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Aircraft()));
            Mockito.when(airportDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Airport()));
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            List<Flight> actual = service.findFlightsByStatus("active");
            assertEquals(actual.size(), flights.size());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindFlightsByStatusException() throws DaoException, ServiceException {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Mockito.when(flightDao.findAllByStatus(Mockito.any(Status.class))).thenThrow(DaoException.class);
        service.findFlightsByStatus("active");
    }

    @Test
    public void testFindFlightByIdSuccess() {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Crew crew = new Crew();
        crew.setDispatcher(new User());
        crew.setStaff(new ArrayList<>());
        Flight flight = new Flight(new Airport(), new Airport(), new Date(0), new Date(0), new Aircraft(), crew,
                new User(), null);
        try {
            Mockito.when(flightDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new FlightDto()));
            Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Aircraft()));
            Mockito.when(airportDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Airport()));
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            Flight actual = service.findFlightById(1);
            assertEquals(actual, flight);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindFlightByIdException() throws DaoException, ServiceException {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Mockito.when(flightDao.findById(Mockito.anyInt())).thenThrow(DaoException.class);
        service.findFlightById(1);
    }

    @Test
    public void testFindUserFlightsByStatusSuccess() {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        List<Flight> flights = new ArrayList<>();
        List<FlightDto> flightsDto = new ArrayList<>();
        flightsDto.add(new FlightDto(1, 2, 3333333333333L,
                3333333333333L, 5, 6, 7, Status.ACTIVE));
        flights.add(new Flight());
        try {
            Mockito.when(flightDao.findUserFlightsByStatus(Mockito.anyInt(), Mockito.any(Status.class))).thenReturn(flightsDto);
            Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Aircraft()));
            Mockito.when(airportDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Airport()));
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            List<Flight> actual = service.findUserFlightsByStatus(new User(), Status.ACTIVE.getStatusName());
            assertEquals(actual.size(), flights.size());
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindUserFlightsByStatusException() throws DaoException, ServiceException {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Mockito.when(flightDao.findUserFlightsByStatus(Mockito.anyInt(), Mockito.any(Status.class))).thenThrow(DaoException.class);
        service.findUserFlightsByStatus(new User(), "active");
    }

    @Test
    public void testUpdateFlightSuccess() {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        try {
            Mockito.when(flightDao.update(Mockito.any(FlightDto.class))).thenReturn(true);
            Mockito.when(userDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(true);
            Mockito.when(aircraftDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new Aircraft()));
            Mockito.when(aircraftDao.update(Mockito.any(Aircraft.class))).thenReturn(true);
            Mockito.when(crewDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new CrewDto()));
            Mockito.when(crewDao.update(Mockito.any(CrewDto.class))).thenReturn(true);
            boolean condition = service.updateFlight(1, "1", "2", "2020-11-05T20:48",
                    "2020-11-05T20:48", "5", "6", "7", "active");
            assertTrue(condition);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateFlightException() throws DaoException, ServiceException {
        FlightService service = ServiceFactory.getInstance().getFlightService();
        Mockito.when(flightDao.update(Mockito.any(FlightDto.class))).thenThrow(DaoException.class);
        service.updateFlight(1, "1", "2", "2020-11-05T20:48",
                "2020-11-05T20:48", "5", "6", "7", "active");
    }
}