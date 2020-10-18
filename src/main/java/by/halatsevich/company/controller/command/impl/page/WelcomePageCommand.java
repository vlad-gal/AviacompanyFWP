package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.controller.util.MessageManager;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WelcomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(WelcomePageCommand.class);
    private static final String EMPTY_FLIGHT_LIST_BUNDLE_MESSAGE = "local.flight.emptyFlightList";
    private static final String EMPTY_AIRCRAFT_LIST_BUNDLE_MESSAGE = "local.aircraft.emptyAircraftList";
    private static final String EMPTY_AIRPORT_LIST_BUNDLE_MESSAGE = "local.airport.emptyAirportList";
    private static final String ERROR_GET_FLIGHT_DATA_FROM_DB_BUNDLE_MESSAGE = "local.error.errorGetFlightDataFromDB";
    private static final String ERROR_GET_AIRCRAFT_DATA_FROM_DB_BUNDLE_MESSAGE = "local.error.errorGetAircraftDataFromDB";
    private static final String ERROR_ERROR_GET_AIRPORT_DATA_FROM_DB_BUNDLE_MESSAGE = "local.error.errorGetAirportDataFromDB";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        FlightService flightService = factory.getFlightService();
        AirportService airportService = factory.getAirportService();
        AircraftService aircraftService = factory.getAircraftService();
        List<Flight> flightList;
        List<Aircraft> aircraftList;
        List<Airport> airportList;
        String lang = (String) request.getSession().getAttribute(ParameterName.SESSION_LANG_ATTRIBUTE);
        MessageManager messageManager = new MessageManager(lang);
        try {
            flightList = flightService.findAllActualFlights();
            if (!flightList.isEmpty()) {
                request.getSession().setAttribute(ParameterName.FLIGHT_LIST, flightList);
                for (Flight flight : flightList) {
                    request.getSession().setAttribute(ParameterName.FLIGHT_DEPARTURE_AIRPORT_NAME, flight.getDepartureAirport().getAirportName());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DEPARTURE_AIRPORT_CITY, flight.getDepartureAirport().getCity());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DEPARTURE_AIRPORT_COUNTRY, flight.getDepartureAirport().getCountry());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DESTINATION_AIRPORT_NAME, flight.getDepartureAirport().getAirportName());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DESTINATION_AIRPORT_CITY, flight.getDepartureAirport().getCity());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DESTINATION_AIRPORT_COUNTRY, flight.getDepartureAirport().getCountry());
                    request.getSession().setAttribute(ParameterName.FLIGHT_DEPART_TIME, flight.getDepartTime());
                    request.getSession().setAttribute(ParameterName.FLIGHT_ARRIVE_TIME, flight.getArriveTime());
                }
            } else {
                request.setAttribute(ParameterName.EMPTY_FLIGHT_LIST, messageManager.getMessage(EMPTY_FLIGHT_LIST_BUNDLE_MESSAGE));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all flights", e);
            request.setAttribute(ParameterName.ERROR_GET_FLIGHT_DATA_FROM_DB, messageManager.getMessage(ERROR_GET_FLIGHT_DATA_FROM_DB_BUNDLE_MESSAGE));
        }
        try {
            aircraftList = aircraftService.findAllAircrafts();
            if (!aircraftList.isEmpty()) {
                request.getSession().setAttribute(ParameterName.AIRCRAFT_LIST, aircraftList);
                for (Aircraft aircraft : aircraftList) {
                    request.getSession().setAttribute(ParameterName.AIRCRAFT_TAIL_NUMBER, aircraft.getTailNumber());
                    request.getSession().setAttribute(ParameterName.AIRCRAFT_NAME, aircraft.getAircraftName());
                    request.getSession().setAttribute(ParameterName.AIRCRAFT_TYPE, aircraft.getAircraftType().toString().toLowerCase());
                }
            } else {
                request.setAttribute(ParameterName.EMPTY_AIRCRAFT_LIST, messageManager.getMessage(EMPTY_AIRCRAFT_LIST_BUNDLE_MESSAGE));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all aircrafts", e);
            request.setAttribute(ParameterName.ERROR_GET_AIRCRAFT_DATA_FROM_DB, messageManager.getMessage(ERROR_GET_AIRCRAFT_DATA_FROM_DB_BUNDLE_MESSAGE));
        }
        try {
            airportList = airportService.findAllAirports();
            if (!airportList.isEmpty()) {
                request.getSession().setAttribute(ParameterName.AIRPORT_LIST, airportList);
                for (Airport airport : airportList) {
                    request.getSession().setAttribute(ParameterName.AIRPORT_NAME, airport.getAirportName());
                    request.getSession().setAttribute(ParameterName.AIRPORT_CITY, airport.getCity());
                    request.getSession().setAttribute(ParameterName.AIRPORT_COUNTRY, airport.getCountry());
                }
            } else {
                request.setAttribute(ParameterName.EMPTY_AIRPORT_LIST, messageManager.getMessage(EMPTY_AIRPORT_LIST_BUNDLE_MESSAGE));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all airports", e);
            request.setAttribute(ParameterName.ERROR_GET_AIRPORT_DATA_FROM_DB, messageManager.getMessage(ERROR_ERROR_GET_AIRPORT_DATA_FROM_DB_BUNDLE_MESSAGE));
        }
        request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.WELCOME_PAGE);
        request.getRequestDispatcher(PagePath.WELCOME_PAGE).forward(request, response);
    }
}
