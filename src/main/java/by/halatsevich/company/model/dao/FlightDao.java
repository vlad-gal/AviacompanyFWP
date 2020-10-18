package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.FlightDto;

import java.util.List;
import java.util.Optional;

public interface FlightDao extends BaseDao {
    List<FlightDto> findAllFlights() throws DaoException;

    Optional<FlightDto> findFlightByDepartureAirportId(int departureAirportId) throws DaoException;

    Optional<FlightDto> findFlightByDestinationAirportId(int destinationAirportId) throws DaoException;

    Optional<FlightDto> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws DaoException;

    Optional<FlightDto> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws DaoException;

    Optional<FlightDto> findFlightByCrewId(int crewId) throws DaoException;

    Optional<FlightDto> findFlightByOperatorId(int operatorId) throws DaoException;

    boolean addFlight(FlightDto flightDto) throws DaoException;

    boolean updateFlight(FlightDto flightDto) throws DaoException;

    boolean removeFlight(int flightId) throws DaoException;
}
