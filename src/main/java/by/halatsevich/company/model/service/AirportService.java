package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports() throws ServiceException;
}
