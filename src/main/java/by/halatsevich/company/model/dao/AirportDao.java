package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AirportDao extends BaseDao<Airport> {

    boolean addAirport(Airport airport) throws DaoException;

}
