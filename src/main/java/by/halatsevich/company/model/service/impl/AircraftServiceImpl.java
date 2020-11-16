package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;

import java.util.List;
import java.util.Optional;

/**
 * The class represents aircraft service implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AircraftServiceImpl implements AircraftService {

    @Override
    public boolean addAircraft(Aircraft aircraft) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        boolean isAdded;
        try {
            isAdded = aircraftDao.addAircraft(aircraft);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding aircraft", e);
        }
        return isAdded;
    }

    @Override
    public List<Aircraft> findAllAircrafts() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        List<Aircraft> aircrafts;
        try {
            aircrafts = aircraftDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all aircrafts", e);
        }
        return aircrafts;
    }

    @Override
    public Optional<Aircraft> findAircraftById(String aircraftId) throws ServiceException {
        int id = Integer.parseInt(aircraftId);
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        Optional<Aircraft> aircraft;
        try {
            aircraft = aircraftDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding aircraft by id", e);
        }
        return aircraft;
    }

    @Override
    public List<Aircraft> findAircraftsByStatus(String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        List<Aircraft> aircrafts;
        try {
            aircrafts = aircraftDao.findAllByStatus(Status.valueOf(status.toUpperCase()));
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all aircrafts by status", e);
        }
        return aircrafts;
    }

    @Override
    public boolean updateAircraft(Aircraft updatingAircraft) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        boolean isUpdated;
        try {
            isUpdated = aircraftDao.update(updatingAircraft);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating aircraft", e);
        }
        return isUpdated;
    }
}
