package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents airport service.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface AirportService {

    /**
     * Add airport.
     *
     * @param airportName the airport name
     * @param city        the city
     * @param country     the country
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addAirport(String airportName, String city, String country) throws ServiceException;

    /**
     * Find all airports.
     *
     * @return the list of airports
     * @throws ServiceException the service exception
     */
    List<Airport> findAllAirports() throws ServiceException;

    /**
     * Find airport by id.
     *
     * @param airportId the airport id
     * @return the optional of airport
     * @throws ServiceException the service exception
     */
    Optional<Airport> findAirportById(String airportId) throws ServiceException;

    /**
     * Update airport.
     *
     * @param updatingAirport the updating airport
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateAirport(Airport updatingAirport) throws ServiceException;
}
