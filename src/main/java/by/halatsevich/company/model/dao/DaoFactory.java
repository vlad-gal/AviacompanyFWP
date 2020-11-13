package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.dao.impl.*;

/**
 * The class represents dao factory.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final UserDao userDao = new UserDaoImpl();
    private final FlightDao flightDao = new FlightDaoImpl();
    private final CrewDao crewDao = new CrewDaoImpl();
    private final AirportDao airportDao = new AirportDaoImpl();
    private final AircraftDao aircraftDao = new AircraftDaoImpl();

    private DaoFactory() {
    }

    /**
     * Gets factory instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return instance;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets flight dao.
     *
     * @return the flight dao
     */
    public FlightDao getFlightDao() {
        return flightDao;
    }

    /**
     * Gets crew dao.
     *
     * @return the crew dao
     */
    public CrewDao getCrewDao() {
        return crewDao;
    }

    /**
     * Gets airport dao.
     *
     * @return the airport dao
     */
    public AirportDao getAirportDao() {
        return airportDao;
    }

    /**
     * Gets aircraft dao.
     *
     * @return the aircraft dao
     */
    public AircraftDao getAircraftDao() {
        return aircraftDao;
    }
}
