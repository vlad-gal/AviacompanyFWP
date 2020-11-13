package by.halatsevich.company.model.service;

import by.halatsevich.company.model.service.impl.*;

/**
 * The class represents service factory.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FlightService flightService = new FlightServiceImpl();
    private final CrewService crewService = new CrewServiceImpl();
    private final AirportService airportService = new AirportServiceImpl();
    private final AircraftService aircraftService = new AircraftServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets factory instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets flight service.
     *
     * @return the flight service
     */
    public FlightService getFlightService() {
        return flightService;
    }

    /**
     * Gets crew service.
     *
     * @return the crew service
     */
    public CrewService getCrewService() {
        return crewService;
    }

    /**
     * Gets airport service.
     *
     * @return the airport service
     */
    public AirportService getAirportService() {
        return airportService;
    }

    /**
     * Gets aircraft service.
     *
     * @return the aircraft service
     */
    public AircraftService getAircraftService() {
        return aircraftService;
    }
}
