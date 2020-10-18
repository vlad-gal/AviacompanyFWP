package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportDao extends BaseDao {

    List<Airport> findAllAirports() throws DaoException;

    Optional<Airport> findAirportById(int airportId) throws DaoException;

    Optional<Airport> findAirportByName(String airportName) throws DaoException;

    List<Airport> findAirportsByCity(String cityName) throws DaoException;

    List<Airport> findAirportsByCountry(String countryName) throws DaoException;

    boolean addAirport(Airport airport) throws DaoException;

    boolean updateAirport(Airport airport) throws DaoException;
}
