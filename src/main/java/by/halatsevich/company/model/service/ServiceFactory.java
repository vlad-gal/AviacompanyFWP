package by.halatsevich.company.model.service;

import by.halatsevich.company.model.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FlightService flightService = new FlightServiceImpl();
    private final CrewService crewService = new CrewServiceImpl();
    private final AirportService airportService = new AirportServiceImpl();
    private final AircraftService aircraftService = new AircraftServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public CrewService getCrewService() {
        return crewService;
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public AircraftService getAircraftService() {
        return aircraftService;
    }
}
