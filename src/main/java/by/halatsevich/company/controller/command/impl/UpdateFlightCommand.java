package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Flight;
import by.halatsevich.company.entity.FlightDto;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.util.DateParser;
import by.halatsevich.company.validator.BaseValidator;
import by.halatsevich.company.validator.FlightValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents update crew command.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateFlightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Flight updatingFlight = (Flight) session.getAttribute(ParameterName.UPDATING_FLIGHT);
        String departureAirportId = request.getParameter(ParameterName.DEPARTURE_AIRPORT);
        String destinationAirportId = request.getParameter(ParameterName.DESTINATION_AIRPORT);
        String departTime = request.getParameter(ParameterName.DEPART_TIME);
        String arriveTime = request.getParameter(ParameterName.ARRIVE_TIME);
        String crewId = request.getParameter(ParameterName.CREW);
        String aircraftId = request.getParameter(ParameterName.AIRCRAFT);
        String operatorId = request.getParameter(ParameterName.OPERATOR);
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        if (BaseValidator.isValidId(departureAirportId)
                && BaseValidator.isValidId(destinationAirportId)
                && BaseValidator.isValidId(crewId)
                && BaseValidator.isValidId(aircraftId)
                && BaseValidator.isValidId(operatorId)
                && BaseValidator.isValidStatus(status)
                && FlightValidator.isValidTime(departTime)
                && FlightValidator.isValidTime(arriveTime)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService flightService = factory.getFlightService();
            try {
                long parsedDepartTime = DateParser.parseDate(departTime).getTime();
                long parsedArriveTime = DateParser.parseDate(arriveTime).getTime();
                FlightDto updatingFlightDto = new FlightDto(updatingFlight.getId(), Integer.parseInt(departureAirportId),
                        Integer.parseInt(destinationAirportId), parsedDepartTime, parsedArriveTime, Integer.parseInt(aircraftId),
                        Integer.parseInt(crewId), Integer.parseInt(operatorId), Status.valueOf(status.toUpperCase()));
                boolean isFlightUpdate = flightService.updateFlight(updatingFlightDto);
                if (isFlightUpdate) {
                    request.setAttribute(ParameterName.UPDATING_SUCCESSFUL_FLAG, true);
                    request.setAttribute(ParameterName.UPDATING_FLIGHT, flightService.findFlightById(updatingFlight.getId()));
                    page = PagePath.UPDATE_FLIGHT;
                } else {
                    logger.log(Level.ERROR, "Error while updating flight");
                    request.setAttribute(ParameterName.ERROR_UPDATE_FLIGHT_FLAG, true);
                    request.setAttribute(ParameterName.UPDATING_FLIGHT, updatingFlight);
                    page = PagePath.UPDATE_FLIGHT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot updating flight", e);
                session.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            session.setAttribute(ParameterName.UPDATING_FLIGHT, updatingFlight);
            page = PagePath.UPDATE_FLIGHT;
        }
        return page;
    }
}
