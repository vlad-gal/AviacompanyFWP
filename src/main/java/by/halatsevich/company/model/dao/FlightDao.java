package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.FlightDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;

import java.util.List;

/**
 * The interface represents flight dao.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface FlightDao extends BaseDao<FlightDto> {

    /**
     * Add flight dto.
     *
     * @param flightDto the flight dto
     * @return true if adding successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean addFlight(FlightDto flightDto) throws DaoException;

    /**
     * Find user flights by status.
     *
     * @param userId the user id
     * @param status the status
     * @return the list of user's flights
     * @throws DaoException the dao exception
     */
    List<FlightDto> findUserFlightsByStatus(int userId, Status status) throws DaoException;
}
