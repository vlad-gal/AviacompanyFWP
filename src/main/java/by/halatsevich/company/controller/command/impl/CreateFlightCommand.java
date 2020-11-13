package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.*;
import by.halatsevich.company.validator.BaseValidator;
import by.halatsevich.company.validator.FlightValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents creating flight command.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CreateFlightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String departureAirportId = request.getParameter(ParameterName.DEPARTURE_AIRPORT);
        String destinationAirportId = request.getParameter(ParameterName.DESTINATION_AIRPORT);
        String departTime = request.getParameter(ParameterName.DEPART_TIME);
        String arriveTime = request.getParameter(ParameterName.ARRIVE_TIME);
        String crewId = request.getParameter(ParameterName.CREW);
        String aircraftId = request.getParameter(ParameterName.AIRCRAFT);
        User operator = (User) session.getAttribute(ParameterName.USER);
        String page;
        if (BaseValidator.isValidId(departureAirportId)
                && BaseValidator.isValidId(destinationAirportId)
                && BaseValidator.isValidId(crewId)
                && BaseValidator.isValidId(aircraftId)
                && FlightValidator.isValidTime(departTime)
                && FlightValidator.isValidTime(arriveTime)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService flightService = factory.getFlightService();
            AircraftService aircraftService = factory.getAircraftService();
            AirportService airportService = factory.getAirportService();
            CrewService crewService = factory.getCrewService();
            try {
                boolean isFlightCreate = flightService
                        .addFlight(departureAirportId, destinationAirportId, departTime, arriveTime, crewId, aircraftId, operator.getId());
                if (isFlightCreate) {
                    request.setAttribute(ParameterName.CREATE_FLIGHT_SUCCESSFUL_FLAG, true);
                    page = PagePath.CREATE_FLIGHT;
                } else {
                    logger.log(Level.ERROR, "Error while creating flight");
                    request.setAttribute(ParameterName.ERROR_CREATE_FLIGHT_FLAG, true);
                    request.setAttribute(ParameterName.DEPARTURE_AIRPORT, airportService.findAirportById(departureAirportId).orElse(null));
                    request.setAttribute(ParameterName.DESTINATION_AIRPORT, airportService.findAirportById(destinationAirportId).orElse(null));
                    request.setAttribute(ParameterName.AIRCRAFT, aircraftService.findAircraftById(aircraftId).orElse(null));
                    request.setAttribute(ParameterName.CREW, crewService.findCrewById(Integer.parseInt(crewId)));
                    request.setAttribute(ParameterName.DEPART_TIME, departTime);
                    request.setAttribute(ParameterName.ARRIVE_TIME, arriveTime);
                    page = PagePath.CREATE_FLIGHT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create flight", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CREATE_FLIGHT;
        }
        return page;
    }
}
