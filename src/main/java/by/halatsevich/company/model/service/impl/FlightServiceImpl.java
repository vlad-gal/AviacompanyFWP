package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.entity.*;
import by.halatsevich.company.model.dao.*;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class represents flight service implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class FlightServiceImpl implements FlightService {

    @Override
    public boolean addFlight(FlightDto flightDto) throws ServiceException {
        boolean isAdded;
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        try {
            isAdded = flightDao.addFlight(flightDto);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding flight", e);
        }
        return isAdded;
    }

    @Override
    public List<Flight> findFlightsByStatus(String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        List<FlightDto> flightDtos;
        List<Flight> flights = new ArrayList<>();
        try {
            flightDtos = flightDao.findAllByStatus(Status.valueOf(status.toUpperCase()));
            for (FlightDto flightDto : flightDtos) {
                Flight flight = createFlight(factory, flightDto);
                flights.add(flight);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all flights by status", e);
        }
        return flights.stream().filter(flight -> flight.getArriveTime().compareTo(new Date()) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Flight findFlightById(int flightId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        Flight flight;
        try {
            Optional<FlightDto> optionalFlightDto = flightDao.findById(flightId);
            if (optionalFlightDto.isPresent()) {
                flight = createFlight(factory, optionalFlightDto.get());
            } else {
                throw new ServiceException("Error while finding flight by id, flight not found");
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding flight by id", e);
        }
        return flight;
    }

    @Override
    public List<Flight> findUserFlightsByStatus(User user, String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        List<FlightDto> flightDtos;
        List<Flight> flights = new ArrayList<>();
        try {
            flightDtos = flightDao.findUserFlightsByStatus(user.getId(), Status.valueOf(status.toUpperCase()));
            for (FlightDto flightDto : flightDtos) {
                Flight flight = createFlight(factory, flightDto);
                flights.add(flight);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all user flights by status", e);
        }
        return flights.stream().filter(flight -> flight.getArriveTime().compareTo(new Date()) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateFlight(FlightDto flightDto) throws ServiceException {
        boolean isUpdated;
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        CrewDao crewDao = factory.getCrewDao();
        UserDao userDao = factory.getUserDao();
        AircraftDao aircraftDao = factory.getAircraftDao();
        try {
            Optional<CrewDto> optionalCrewDto = crewDao.findById(flightDto.getCrewId());
            Optional<Aircraft> optionalAircraft = aircraftDao.findById(flightDto.getAircraftId());
            if (optionalCrewDto.isPresent() && optionalAircraft.isPresent()) {
                CrewDto crewDto = optionalCrewDto.get();
                Aircraft aircraft = optionalAircraft.get();
                switch (flightDto.getStatus()) {
                    case FLY:
                        crewDto.setStatus(Status.FLY);
                        crewDao.update(crewDto);
                        aircraft.setStatus(Status.FLY);
                        aircraftDao.update(aircraft);
                        break;
                    case ACTIVE:
                    case INACTIVE:
                        crewDto.setStatus(Status.ACTIVE);
                        crewDao.update(crewDto);
                        aircraft.setStatus(Status.ACTIVE);
                        aircraftDao.update(aircraft);
                        break;
                }
                List<Integer> usersId = crewDao.findUserIdsByCrewId(flightDto.getCrewId());
                for (int id : usersId) {
                    Optional<User> optionalUser = userDao.findById(id);
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        switch (flightDto.getStatus()) {
                            case ACTIVE:
                                user.setStatus(Status.BUSY);
                                userDao.update(user);
                                break;
                            case INACTIVE:
                                user.setStatus(Status.ACTIVE);
                                userDao.update(user);
                                break;
                            case FLY:
                                user.setStatus(Status.FLY);
                                userDao.update(user);
                                break;
                        }
                    }
                }
            }
            isUpdated = flightDao.update(flightDto);
        } catch (DaoException e) {
            throw new ServiceException("Error while update flight", e);
        }
        return isUpdated;
    }

    private Flight createFlight(DaoFactory factory, FlightDto flightDto) throws DaoException {
        UserDao userDao = factory.getUserDao();
        AircraftDao aircraftDao = factory.getAircraftDao();
        AirportDao airportDao = factory.getAirportDao();
        CrewDao crewDao = factory.getCrewDao();
        Airport departureAirport = airportDao.findById(flightDto.getDepartureAirportId()).orElse(null);
        Airport destinationAirport = airportDao.findById(flightDto.getDestinationAirportId()).orElse(null);
        Date arriveTime = new Date(flightDto.getArriveTime());
        Date departTime = new Date(flightDto.getDepartTime());
        Aircraft aircraft = aircraftDao.findById(flightDto.getAircraftId()).orElse(null);
        User operator = userDao.findById(flightDto.getOperatorId()).orElse(null);
        Status flightStatus = flightDto.getStatus();
        Optional<CrewDto> optionalCrewDto = crewDao.findById(flightDto.getCrewId());
        CrewDto crewDto = optionalCrewDto.orElse(null);
        Crew crew = createCrew(userDao, crewDao, crewDto);
        return new Flight(flightDto.getId(), departureAirport, destinationAirport, departTime, arriveTime, aircraft,
                crew, operator, flightStatus);
    }

    private Crew createCrew(UserDao userDao, CrewDao crewDao, CrewDto crewDto) throws DaoException {
        User dispatcher = userDao.findById(crewDto.getDispatcherId()).orElse(null);
        List<Integer> usersIdByCrewId = crewDao.findUserIdsByCrewId(crewDto.getId());
        List<User> staff = new ArrayList<>();
        for (int userId : usersIdByCrewId) {
            staff.add(userDao.findById(userId).orElse(null));
        }
        int numberOfPilots = crewDto.getNumberOfPilots();
        int numberOfNavigators = crewDto.getNumberOfNavigators();
        int numberOfRadioman = crewDto.getNumberOfRadioman();
        int numberOfStewardesses = crewDto.getNumberOfStewardesses();
        Status crewStatus = crewDto.getStatus();
        String crewName = crewDto.getCrewName();
        return new Crew(crewDto.getId(), dispatcher, crewName, staff, numberOfPilots, numberOfNavigators, numberOfRadioman,
                numberOfStewardesses, crewStatus);
    }
}
