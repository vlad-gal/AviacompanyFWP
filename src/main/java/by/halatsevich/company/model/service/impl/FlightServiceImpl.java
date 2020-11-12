package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.*;
import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.util.DateParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

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
            throw new ServiceException("Error while finding all flights", e);
        }
        return flights.stream().filter(flight -> flight.getArriveTime().compareTo(new Date()) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Flight findFlightById(int flightId) throws ServiceException {
        try {
            return createFlight(flightId);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding flight by id");
        }
    }

    @Override
    public List<Flight> findUsersFlightsByStatus(User user, String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        List<FlightDto> flightDtos;
        List<Flight> flights = new ArrayList<>();
        try {
            flightDtos = flightDao.findUsersFlightsByStatus(user.getId(), Status.valueOf(status.toUpperCase()));
            for (FlightDto flightDto : flightDtos) {
                Flight flight = createFlight(factory, flightDto);
                flights.add(flight);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding all flights", e);
        }
        return flights.stream().filter(flight -> flight.getArriveTime().compareTo(new Date()) > 0)
                .collect(Collectors.toList());
    }

    private Flight createFlight(int flightId) throws DaoException {
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        Flight flight = null;
        Optional<FlightDto> optionalFlightDto = flightDao.findById(flightId);
        if (optionalFlightDto.isPresent()) {
            flight = createFlight(factory, optionalFlightDto.get());
        }
        return flight;
    }

    @Override
    public boolean addFlight(String departureAirportId, String destinationAirportId, String departTime,
                             String arriveTime, String crewId, String aircraftId, int operatorId) throws ServiceException {
        boolean isAdded;
        int departureAirport = Integer.parseInt(departureAirportId);
        int destinationAirport = Integer.parseInt(destinationAirportId);
        long depart = DateParser.parseDate(departTime).getTime();
        long arrive = DateParser.parseDate(arriveTime).getTime();
        int crew = Integer.parseInt(crewId);
        int aircraft = Integer.parseInt(aircraftId);
        FlightDto flightDto = new FlightDto(departureAirport, destinationAirport, depart, arrive, aircraft, crew,
                operatorId, Status.ACTIVE);
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        CrewDao crewDao = factory.getCrewDao();
        UserDao userDao = factory.getUserDao();
        try {
            isAdded = flightDao.addFlight(flightDto);
            List<Integer> usersId = crewDao.findUsersIdByCrewId(crew);
            for (int id : usersId) {
                Optional<User> optionalUser = userDao.findById(id);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setStatus(Status.BUSY);
                    userDao.update(user);
                }
            } // TODO: 12.11.2020 +aircraft
        } catch (DaoException e) {
            throw new ServiceException("Error while adding flight", e);
        }
        return isAdded;
    }

    @Override
    public boolean updateFlight(int flightId, String departureAirportId, String destinationAirportId, String departTime,
                                String arriveTime, String crewId, String aircraftId,
                                String operatorId, String status) throws ServiceException {
        boolean isUpdated;
        int departureAirport = Integer.parseInt(departureAirportId);
        int destinationAirport = Integer.parseInt(destinationAirportId);
        long depart = DateParser.parseDate(departTime).getTime();
        long arrive = DateParser.parseDate(arriveTime).getTime();
        int crew = Integer.parseInt(crewId);
        int aircraft = Integer.parseInt(aircraftId);
        int operator = Integer.parseInt(operatorId);
        Status parsedStatus = Status.valueOf(status.toUpperCase());
        FlightDto flightDto = new FlightDto(flightId, departureAirport, destinationAirport, depart, arrive, aircraft,
                crew, operator, parsedStatus);
        DaoFactory factory = DaoFactory.getInstance();
        FlightDao flightDao = factory.getFlightDao();
        CrewDao crewDao = factory.getCrewDao();
        UserDao userDao = factory.getUserDao();
        try {
            isUpdated = flightDao.update(flightDto);
            Optional<CrewDto> optionalCrewDto = crewDao.findById(crew);
            if (optionalCrewDto.isPresent()) {
                CrewDto crewDto = optionalCrewDto.get();
                switch (parsedStatus) {
                    case FLY:
                        crewDto.setStatus(Status.FLY);
                        crewDao.update(crewDto);
                        break;
                    case INACTIVE:
                        crewDto.setStatus(Status.ACTIVE);
                        crewDao.update(crewDto);
                        break;
                }
                List<Integer> usersId = crewDao.findUsersIdByCrewId(crew);
                for (int id : usersId) {
                    Optional<User> optionalUser = userDao.findById(id);
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        switch (parsedStatus) {
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
            }// TODO: 12.11.2020  aircraft
        } catch (DaoException e) {
            throw new ServiceException("Error while adding flight", e);
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
        List<Integer> usersIdByCrewId = crewDao.findUsersIdByCrewId(crewDto.getId());
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
        return new Crew(dispatcher, crewName, staff, numberOfPilots, numberOfNavigators, numberOfRadioman,
                numberOfStewardesses, crewStatus);
    }
}
