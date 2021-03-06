package by.halatsevich.company.model.dao;

import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.model.exception.DaoException;

/**
 * The interface represents aircraft dao. Extends the interface BaseDao, defines specific methods
 * which interactions with Aircraft entities in database.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface AircraftDao extends BaseDao<Aircraft> {

    /**
     * Add aircraft.
     *
     * @param aircraft the aircraft
     * @return true if adding successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean addAircraft(Aircraft aircraft) throws DaoException;
}
