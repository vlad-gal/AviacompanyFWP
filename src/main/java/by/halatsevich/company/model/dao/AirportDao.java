package by.halatsevich.company.model.dao;

import by.halatsevich.company.entity.Airport;
import by.halatsevich.company.model.exception.DaoException;

/**
 * The interface represents airport dao. Extends the interface BaseDao, defines specific methods
 * which interactions with Airport entities in database.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface AirportDao extends BaseDao<Airport> {

    /**
     * Add airport.
     *
     * @param airport the airport
     * @return true if adding successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean addAirport(Airport airport) throws DaoException;
}
