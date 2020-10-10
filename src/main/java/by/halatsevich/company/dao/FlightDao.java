package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightDao extends BaseDao {
    List<Flight> findAllFlights() throws DaoException;

    Optional<Flight> findFlightByDepartureAirportId(int departureAirportId) throws DaoException;

    Optional<Flight> findFlightByDestinationAirportId(int destinationAirportId) throws DaoException;

    Optional<Flight> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException;

    Optional<Flight> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException;

    Optional<Flight> findFlightByCrewId(int crewId) throws DaoException;

    Optional<Flight> findFlightByOperatorId(int operatorId) throws DaoException;

    boolean addFlight(Flight flight) throws DaoException;

    boolean updateFlight(Flight flight) throws DaoException;

    boolean removeFlight(int flightId) throws DaoException;
}
