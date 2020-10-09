package by.halatsevich.company.service;

import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.service.exception.ServiceException;

import java.util.List;

public interface FlightService {
     List<Aircraft> selectAllAircraft() throws ServiceException;


     //хз
     boolean createFlight();
     boolean updateFlight();
     boolean removeFlight();
     boolean selectFlight();

     boolean createAirport();
     boolean updateAirport();
     boolean removeAirport();
     boolean selectAirport();

     boolean createAircraft();
     boolean updateAircraft();
     boolean removeAircraft();
     boolean selectAircraft();

     boolean createAircraftTypes();
     boolean updateAircraftTypes();
     boolean removeAircraftTypes();
     boolean selectAircraftTypes();






}
