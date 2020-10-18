package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.entity.FlightDto;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface FlightService {

     List<Flight> findAllFlights() throws ServiceException;
     List<Flight> findAllActualFlights() throws ServiceException;

     Optional<Flight> findFlightByDepartureAirportId(int departureAirportId) throws ServiceException;

     Optional<Flight> findFlightByDestinationAirportId(int destinationAirportId) throws ServiceException;

     Optional<Flight> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws ServiceException;

     Optional<Flight> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws ServiceException;

     Optional<Flight> findFlightByCrewId(int crewId) throws ServiceException;

     Optional<Flight> findFlightByOperatorId(int operatorId) throws ServiceException;

     boolean addFlight(Flight flight) throws ServiceException;

     boolean updateFlight(Flight flight) throws ServiceException;

     boolean removeFlight(int flightId) throws ServiceException;

}
