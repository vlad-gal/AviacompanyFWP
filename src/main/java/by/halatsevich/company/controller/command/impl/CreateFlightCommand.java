package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import by.halatsevich.company.validator.FlightValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateFlightCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String departureAirportId = request.getParameter(ParameterName.DEPARTURE_AIRPORT);
        String destinationAirportId = request.getParameter(ParameterName.DESTINATION_AIRPORT);
        String departTime = request.getParameter(ParameterName.DEPART_TIME);
        String arriveTime = request.getParameter(ParameterName.ARRIVE_TIME);
        String crewId = request.getParameter(ParameterName.CREW);
        String aircraftId = request.getParameter(ParameterName.AIRCRAFT);
        String operatorId = request.getParameter(ParameterName.OPERATOR); // TODO: 04.11.2020 это будет команда оператора достаем из сессии
        String page;
        if (BaseValidator.isValidId(departureAirportId)
                && BaseValidator.isValidId(destinationAirportId)
                && BaseValidator.isValidId(crewId)
                && BaseValidator.isValidId(aircraftId)
                && BaseValidator.isValidId(operatorId)
                && FlightValidator.isValidTime(departTime)
                && FlightValidator.isValidTime(arriveTime)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService flightService = factory.getFlightService();
            try {
                boolean isFlightCreate = flightService
                        .addFlight(departureAirportId, destinationAirportId, departTime, arriveTime, crewId, aircraftId, operatorId);
                if (isFlightCreate) {
                    request.setAttribute(ParameterName.CREATE_FLIGHT_SUCCESSFUL_FLAG, true);
                    page = PagePath.CREATE_FLIGHT;
                } else {
                    request.setAttribute(ParameterName.ERROR_CREATE_FLIGHT_FLAG, true);

                    page = PagePath.CREATE_FLIGHT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create flight", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CREATE_FLIGHT;
        }
        return page;
    }
}
