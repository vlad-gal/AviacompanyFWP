package by.halatsevich.company.model.service;

import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents aircraft service.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface AircraftService {

    /**
     * Add aircraft.
     *
     * @param aircraft the aircraft
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addAircraft(Aircraft aircraft) throws ServiceException;

    /**
     * Find all aircrafts.
     *
     * @return the list of aircrafts
     * @throws ServiceException the service exception
     */
    List<Aircraft> findAllAircrafts() throws ServiceException;

    /**
     * Find aircraft by id.
     *
     * @param aircraftId the aircraft id
     * @return the optional of aircraft
     * @throws ServiceException the service exception
     */
    Optional<Aircraft> findAircraftById(String aircraftId) throws ServiceException;

    /**
     * Find aircrafts by status.
     *
     * @param status the status
     * @return the list of aircrafts
     * @throws ServiceException the service exception
     */
    List<Aircraft> findAircraftsByStatus(String status) throws ServiceException;

    /**
     * Update aircraft.
     *
     * @param updatingAircraft the updating aircraft
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateAircraft(Aircraft updatingAircraft) throws ServiceException;
}
