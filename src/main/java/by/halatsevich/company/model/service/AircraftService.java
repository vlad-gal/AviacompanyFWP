package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;

public interface AircraftService {
    List<Aircraft> findAllAircrafts() throws ServiceException;

}
