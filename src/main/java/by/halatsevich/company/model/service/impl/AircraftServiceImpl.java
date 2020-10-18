package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.AircraftDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;

public class AircraftServiceImpl implements AircraftService {
    @Override
    public List<Aircraft> findAllAircrafts() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        AircraftDao aircraftDao = factory.getAircraftDao();
        List<Aircraft> aircrafts;
        try {
            aircrafts = aircraftDao.findAllAircrafts();
        } catch (DaoException e) {
            throw new ServiceException("",e);
        }
        return aircrafts;
    }
}
