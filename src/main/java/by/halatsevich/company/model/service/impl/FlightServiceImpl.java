package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.*;
import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.comparator.FlightDepartDateComparator;
import by.halatsevich.company.validator.BaseValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> findAllFlights() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        List<FlightDto> flightDtos;
        List<Flight> flights = new ArrayList<>();
        try {
            flightDtos = flightDao.findAll();
            for (FlightDto flightDto : flightDtos) {
                flights.add(createFlight(factory, flightDto));
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
    public Flight findFlightById(String flightId) throws ServiceException {
        int id = Integer.parseInt(flightId);
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        Flight flight = null;
        try {
            Optional<FlightDto> optionalFlightDto = flightDao.findById(id);
            if (optionalFlightDto.isPresent()) {
                flight = createFlight(factory, optionalFlightDto.get());
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding flight by id");
        }
        return flight;
    }

    private Flight createFlight(DaoFactory factory, FlightDto flightDto) throws DaoException, ServiceException {
        UserDao userDao = factory.getUserDao();
        AircraftDao aircraftDao = factory.getAircraftDao();
        AirportDao airportDao = factory.getAirportDao();
        CrewDao crewDao = factory.getCrewDao();
        Optional<Airport> optionalDepartureAirport = airportDao.findById(flightDto.getDepartureAirportId());
        Airport departureAirport;
        if (optionalDepartureAirport.isPresent()) {
            departureAirport = optionalDepartureAirport.get();
        } else {
            throw new ServiceException("Departure airport not found");
        }
        Optional<Airport> optionalDestinationAirport = airportDao.findById(flightDto.getDestinationAirportId());
        Airport destinationAirport;
        if (optionalDestinationAirport.isPresent()) {
            destinationAirport = optionalDestinationAirport.get();
        } else {
            throw new ServiceException("Destination airport not fount");
        }
        Date arriveTime = new Date(flightDto.getArriveTime());
        Date departTime = new Date(flightDto.getDepartTime());
        Optional<Aircraft> optionalAircraft = aircraftDao.findById(flightDto.getAircraftId());
        Aircraft aircraft;
        if (optionalAircraft.isPresent()) {
            aircraft = optionalAircraft.get();
        } else {
            throw new ServiceException("Aircraft not found");
        }
        Optional<User> optionalOperator = userDao.findById(flightDto.getOperatorId());
        User operator;
        if (optionalOperator.isPresent()) {
            operator = optionalOperator.get();
        } else {
            throw new ServiceException("Operator not found");
        }
        Status flightStatus = flightDto.getStatus();
        Optional<CrewDto> optionalCrewDto = crewDao.findById(flightDto.getCrewId());
        Crew crew = null;
        if (optionalCrewDto.isPresent()) {
            CrewDto crewDto = optionalCrewDto.get();
            crew = createCrew(userDao, crewDao, crewDto);
        }
        return new Flight(flightDto.getId(), departureAirport, destinationAirport, departTime, arriveTime, aircraft, crew, operator, flightStatus);
    }

    private Crew createCrew(UserDao userDao, CrewDao crewDao, CrewDto crewDto) throws DaoException, ServiceException {
        Optional<User> optionalDispatcher = userDao.findById(crewDto.getDispatcherId());
        User dispatcher;
        if (optionalDispatcher.isPresent()) {
            dispatcher = optionalDispatcher.get();
        } else {
            throw new ServiceException("Dispatcher not found");
        }
        List<Integer> usersIdByCrewId = crewDao.findUsersIdByCrewId(crewDto.getId());
        List<User> staff = new ArrayList<>();
        for (int userId : usersIdByCrewId) {
            Optional<User> optionalUser = userDao.findById(userId);
            if (optionalUser.isPresent()) {
                staff.add(optionalUser.get());
            } else {
                throw new ServiceException("Member of crew not found");
            }
        }
        int numberOfPilots = crewDto.getNumberOfPilots();
        int numberOfNavigators = crewDto.getNumberOfNavigators();
        int numberOfRadioman = crewDto.getNumberOfRadioman();
        int numberOfStewardesses = crewDto.getNumberOfStewardesses();
        Status crewStatus = crewDto.getStatus();
        return new Crew(dispatcher, staff, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, crewStatus);
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
