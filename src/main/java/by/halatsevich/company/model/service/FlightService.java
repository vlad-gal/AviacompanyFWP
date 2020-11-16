package by.halatsevich.company.model.service;

import by.halatsevich.company.entity.Flight;
import by.halatsevich.company.entity.FlightDto;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;

/**
 * The interface represents flight service.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface FlightService {

    /**
     * Add flight boolean.
     *
     * @param flightDto the flight dto
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addFlight(FlightDto flightDto) throws ServiceException;

    /**
     * Find flights by status.
     *
     * @param status the status
     * @return the list of flights
     * @throws ServiceException the service exception
     */
    List<Flight> findFlightsByStatus(String status) throws ServiceException;

    /**
     * Find flight by id.
     *
     * @param flightId the flight id
     * @return the flight
     * @throws ServiceException the service exception
     */
    Flight findFlightById(int flightId) throws ServiceException;

    /**
     * Find user flights by status list.
     *
     * @param user   the user
     * @param status the status
     * @return the list of user's flights
     * @throws ServiceException the service exception
     */
    List<Flight> findUserFlightsByStatus(User user, String status) throws ServiceException;


    /**
     * Update flight boolean.
     *
     * @param flightDto the flight dto
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateFlight(FlightDto flightDto) throws ServiceException;
}
