package by.halatsevich.company.dao;

import by.halatsevich.company.dao.impl.FlightDaoImpl;
import by.halatsevich.company.dao.impl.UserDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final FlightDao flightDao = new FlightDaoImpl();

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
}
