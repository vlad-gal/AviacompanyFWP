package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> findAllAircrafts() throws ServiceException;

    Optional<Aircraft> findAircraftById(String aircraftId) throws ServiceException;
}
