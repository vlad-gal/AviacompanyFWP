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
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        List<Aircraft> aircrafts;
        try {
            aircrafts = flightDao.selectAllAircraft();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return aircrafts;
    }
}
