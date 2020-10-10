package by.halatsevich.company.dao;

import by.halatsevich.company.dao.impl.*;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final FlightDao flightDao = new FlightDaoImpl();
    private final CrewDao crewDao = new CrewDaoImpl();
    private final AirportDao airportDao = new AirportDaoImpl();
    private final AircraftDao aircraftDao = new AircraftDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public FlightDao getFlightDao() {
        return flightDao;
    }

    public CrewDao getCrewDao() {
        return crewDao;
    }

    public AirportDao getAirportDao() {
        return airportDao;
    }

    public AircraftDao getAircraftDao() {
        return aircraftDao;
    }
}
