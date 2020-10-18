package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.*;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.comparator.FlightDepartDateComparator;
import by.halatsevich.company.model.service.exception.ServiceException;

import java.util.*;

public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> findAllFlights() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        UserDao userDao = factory.getUserDao();
        AircraftDao aircraftDao = factory.getAircraftDao();
        AirportDao airportDao = factory.getAirportDao();
        CrewDao crewDao = factory.getCrewDao();
        List<FlightDto> flightDtos;
        List<Flight> flights = new ArrayList<>();
        try {
            flightDtos = flightDao.findAllFlights();
            for (FlightDto dto : flightDtos) {
                Airport departureAirport = airportDao.findAirportById(dto.getDepartureAirportId()).get();
                Airport destinationAirport = airportDao.findAirportById(dto.getDestinationAirportId()).get();
                Date arriveTime = new Date(dto.getArriveTime());
                Date departTime = new Date(dto.getDepartTime());
                Aircraft aircraft = aircraftDao.findAircraftById(dto.getAircraftId()).get();
                User operator = userDao.findUserById(dto.getOperatorId()).get();
                Status flightStatus = dto.getStatus();
                //crew
                Optional<CrewDto> crewDtoOptional = crewDao.findCrewById(dto.getCrewId());
                CrewDto crewDto = crewDtoOptional.get();
                User dispatcher = userDao.findUserById(crewDto.getDispatcherId()).get();
                List<Integer> usersIdByCrewId = crewDao.findUsersIdByCrewId(crewDto.getId());
                List<User> staff = new ArrayList<>();
                for (int userId : usersIdByCrewId) {
                    staff.add(userDao.findUserById(userId).get());
                }
                int numberOfPilots = crewDto.getNumberOfPilots();
                int numberOfNavigators = crewDto.getNumberOfNavigators();
                int numberOfRadioman = crewDto.getNumberOfRadioman();
                int numberOfStewardesses = crewDto.getNumberOfStewardesses();
                Status crewStatus = crewDto.getStatus();
                flights.add(new Flight(dto.getId(), departureAirport, destinationAirport, departTime, arriveTime, aircraft,
                        new Crew(crewDto.getId(), dispatcher, staff, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, crewStatus), operator, flightStatus));
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all flights", e);
        }
        return flights;
    }

    @Override
    public List<Flight> findAllActualFlights() throws ServiceException {
        List<Flight> flights = findAllFlights();
        List<Flight> actualFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getStatus() == Status.ACTIVE && (flight.getArriveTime().compareTo(new Date()) > 0)) {
                actualFlights.add(flight);
            }
        }
        actualFlights.sort(new FlightDepartDateComparator());
        return actualFlights;
    }

    @Override
    public Optional<Flight> findFlightByDepartureAirportId(int departureAirportId) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Flight> findFlightByDestinationAirportId(int destinationAirportId) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Flight> findFlightByDepartPeriodOfTime(long departTimeFrom, long departTimeTo) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Flight> findFlightByArrivePeriodOfTime(long arriveTimeFrom, long arriveTimeTo) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Flight> findFlightByCrewId(int crewId) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Flight> findFlightByOperatorId(int operatorId) throws ServiceException {
        return null;
    }

    @Override
    public boolean addFlight(Flight flightDto) throws ServiceException {
        return false;
    }

    @Override
    public boolean updateFlight(Flight flightDto) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeFlight(int flightId) throws ServiceException {
        return false;
    }
}
