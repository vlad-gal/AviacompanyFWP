package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightDao {
    List<Flight> selectAllFlights() throws DaoException;
    Optional<Flight> selectFlightByDepartureAirportId(int departureAirportId) throws DaoException;
    Optional<Flight> selectFlightByDestinationAirportId(int destinationAirportId) throws DaoException;
    Optional<Flight> selectFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException;
    Optional<Flight> selectFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException;
    Optional<Flight> selectFlightByCrewId(int crewId) throws DaoException;
    Optional<Flight> selectFlightByOperatorId(int operatorId) throws DaoException;
    boolean addFlight(Flight flight) throws DaoException;
    Optional<Flight> updateFlight(Flight flight) throws DaoException;
    boolean removeFlight(int flightId) throws DaoException;


}
