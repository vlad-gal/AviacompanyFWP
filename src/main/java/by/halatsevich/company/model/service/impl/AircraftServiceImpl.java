package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;

import java.util.List;
import java.util.Optional;

public class AircraftServiceImpl implements AircraftService {

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
}
