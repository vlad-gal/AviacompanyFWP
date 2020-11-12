package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<Flight> findFlightsByStatus(String status) throws ServiceException;

    boolean addFlight(String departureAirportId, String destinationAirportId, String departTime, String arriveTime, String crewId, String aircraftId, int operatorId) throws ServiceException;

    boolean updateFlight(int flightId, String departureAirportId, String destinationAirportId, String departTime, String arriveTime, String crewId, String aircraftId, String operatorId, String status) throws ServiceException;

    Flight findFlightById(int flightId) throws ServiceException;

    List<Flight> findUsersFlightsByStatus(User user, String status) throws ServiceException;
}
