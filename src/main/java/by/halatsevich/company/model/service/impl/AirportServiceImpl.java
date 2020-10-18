package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.AirportDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    @Override
    public List<Airport> findAllAirports() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AirportDao airportDao = factory.getAirportDao();
        List<Airport> airports;
        try {
            airports = airportDao.findAllAirports();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
        return airports;
    }
}
