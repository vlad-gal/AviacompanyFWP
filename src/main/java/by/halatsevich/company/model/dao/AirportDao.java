package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AirportDao extends BaseDao<Airport> {

    Optional<Airport> findAirportByName(String airportName) throws DaoException;

    List<Airport> findAirportsByCity(String cityName) throws DaoException;

    List<Airport> findAirportsByCountry(String countryName) throws DaoException;

    boolean addAirport(Airport airport) throws DaoException;

}
