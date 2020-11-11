package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> findAllAircrafts() throws ServiceException;

    Optional<Aircraft> findAircraftById(String aircraftId) throws ServiceException;

    List<Aircraft> findAircraftsByStatus(String status) throws ServiceException;

    boolean addAircraft(String tailNumber, String aircraftName, String aircraftType, Status status) throws ServiceException;

    boolean updateAircraft(Aircraft updatingAircraft) throws ServiceException;
}
