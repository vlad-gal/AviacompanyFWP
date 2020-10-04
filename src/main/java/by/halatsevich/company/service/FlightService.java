package by.halatsevich.company.service;

import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.service.exception.ServiceException;

import java.util.List;

public interface FlightService {
     List<Aircraft> selectAllAircraft() throws ServiceException;
}
