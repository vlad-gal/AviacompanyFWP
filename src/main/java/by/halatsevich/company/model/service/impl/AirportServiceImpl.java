package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.entity.Airport;
import by.halatsevich.company.model.dao.AirportDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;

import java.util.List;
import java.util.Optional;

/**
 * The class represents airport service implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AirportServiceImpl implements AirportService {

    @Override
    public boolean addAirport(Airport airport) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AirportDao airportDao = factory.getAirportDao();
        boolean isAdded;
        try {
            isAdded = airportDao.addAirport(airport);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding airport", e);
        }
        return isAdded;
    }

    @Override
    public List<Airport> findAllAirports() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AirportDao airportDao = factory.getAirportDao();
        List<Airport> airports;
        try {
            airports = airportDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all airports", e);
        }
        return airports;
    }

    @Override
    public Optional<Airport> findAirportById(String airportId) throws ServiceException {
        int id = Integer.parseInt(airportId);
        DaoFactory factory = DaoFactory.getInstance();
        AirportDao airportDao = factory.getAirportDao();
        Optional<Airport> airport;
        try {
            airport = airportDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding airport by id", e);
        }
        return airport;
    }

    @Override
    public boolean updateAirport(Airport updatingAirport) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AirportDao airportDao = factory.getAirportDao();
        boolean isUpdated;
        try {
            isUpdated = airportDao.update(updatingAirport);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating airport", e);
        }
        return isUpdated;
    }
}
