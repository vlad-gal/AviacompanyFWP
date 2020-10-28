package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> findAllAirports() throws ServiceException;

    Optional<Airport> findAirportById(String airportId) throws ServiceException;
}
