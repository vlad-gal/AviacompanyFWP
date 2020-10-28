package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.entity.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftDao extends BaseDao<Aircraft> {

    Optional<Aircraft> findAircraftByTailNumber(String tailNumber) throws DaoException;

    List<Aircraft> findAircraftsByName(String aircraftName) throws DaoException;

    List<Aircraft> findAircraftsByType(Aircraft.AircraftType aircraftType) throws DaoException;

    boolean addAircraft(Aircraft aircraft) throws DaoException;
}
