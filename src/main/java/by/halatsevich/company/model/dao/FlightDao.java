package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.entity.FlightDto;

import java.util.List;
import java.util.Optional;

public interface FlightDao extends BaseDao<FlightDto> {

  boolean addFlight(FlightDto flightDto) throws DaoException;

    List<FlightDto> findUsersFlightsByStatus(int userId, Status status) throws DaoException;
}
