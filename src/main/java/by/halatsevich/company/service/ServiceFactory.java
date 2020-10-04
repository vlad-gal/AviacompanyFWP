package by.halatsevich.company.service;

import by.halatsevich.company.service.impl.FlightServiceImpl;
import by.halatsevich.company.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FlightService flightService = new FlightServiceImpl();

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
}
