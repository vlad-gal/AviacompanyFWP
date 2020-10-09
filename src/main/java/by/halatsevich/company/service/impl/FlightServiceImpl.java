package by.halatsevich.company.service.impl;

import by.halatsevich.company.dao.DaoFactory;
import by.halatsevich.company.dao.FlightDao;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.impl.FlightDaoImpl;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.service.FlightService;
import by.halatsevich.company.service.exception.ServiceException;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    @Override
    public List<Aircraft> selectAllAircraft() throws ServiceException{
        return null;
//        DaoFactory factory = DaoFactory.getInstance();
//        FlightDao flightDao = factory.getFlightDao();
//        List<Aircraft> aircrafts;
//        try {
//            aircrafts = flightDao.selectAllAircraft();
//        } catch (DaoException e) {
//            throw new ServiceException(e);
//        }
//        return aircrafts;
    }

    @Override
    public boolean createFlight() {
        return false;
    }

    @Override
    public boolean updateFlight() {
        return false;
    }

    @Override
    public boolean removeFlight() {
        return false;
    }

    @Override
    public boolean selectFlight() {
        return false;
    }

    @Override
    public boolean createAirport() {
        return false;
    }

    @Override
    public boolean updateAirport() {
        return false;
    }

    @Override
    public boolean removeAirport() {
        return false;
    }

    @Override
    public boolean selectAirport() {
        return false;
    }

    @Override
    public boolean createAircraft() {
        return false;
    }

    @Override
    public boolean updateAircraft() {
        return false;
    }

    @Override
    public boolean removeAircraft() {
        return false;
    }

    @Override
    public boolean selectAircraft() {
        return false;
    }

    @Override
    public boolean createAircraftTypes() {
        return false;
    }

    @Override
    public boolean updateAircraftTypes() {
        return false;
    }

    @Override
    public boolean removeAircraftTypes() {
        return false;
    }

    @Override
    public boolean selectAircraftTypes() {
        return false;
    }
}
