package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.entity.User;
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
     * Add flight.
     *
     * @param departureAirportId   the departure airport id
     * @param destinationAirportId the destination airport id
     * @param departTime           the depart time
     * @param arriveTime           the arrive time
     * @param crewId               the crew id
     * @param aircraftId           the aircraft id
     * @param operatorId           the operator id
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addFlight(String departureAirportId, String destinationAirportId, String departTime,
                      String arriveTime, String crewId, String aircraftId, int operatorId) throws ServiceException;

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
     * Update flight.
     *
     * @param flightId             the flight id
     * @param departureAirportId   the departure airport id
     * @param destinationAirportId the destination airport id
     * @param departTime           the depart time
     * @param arriveTime           the arrive time
     * @param crewId               the crew id
     * @param aircraftId           the aircraft id
     * @param operatorId           the operator id
     * @param status               the status
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateFlight(int flightId, String departureAirportId, String destinationAirportId, String departTime,
                         String arriveTime, String crewId, String aircraftId, String operatorId, String status)
            throws ServiceException;
}
