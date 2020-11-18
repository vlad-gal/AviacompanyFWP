package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.*;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.*;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents preparation for forwarding to the update flight page.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateFlightPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateFlightPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String flightId = request.getParameter(ParameterName.FLIGHT_ID);
        String page;
        if (BaseValidator.isValidId(flightId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService flightService = factory.getFlightService();
            CrewService crewService = factory.getCrewService();
            AirportService airportService = factory.getAirportService();
            AircraftService aircraftService = factory.getAircraftService();
            UserService userService = factory.getUserService();
            List<Crew> crews;
            List<Airport> airports;
            List<Aircraft> aircrafts;
            List<User> operators;
            try {
                Flight flight = flightService.findFlightById(Integer.parseInt(flightId));
                crews = crewService.findAllCrews();
                airports = airportService.findAllAirports();
                aircrafts = aircraftService.findAllAircrafts();
                operators = userService
                        .findUsersByRoleAndStatus(User.Role.OPERATOR.getRoleName(), Status.ACTIVE.getStatusName());
                session.setAttribute(ParameterName.UPDATING_FLIGHT, flight);
                session.setAttribute(ParameterName.CREWS, crews);
                session.setAttribute(ParameterName.AIRPORTS, airports);
                session.setAttribute(ParameterName.AIRCRAFTS, aircrafts);
                session.setAttribute(ParameterName.OPERATORS, operators);
                page = PagePath.UPDATE_FLIGHT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding flight by id", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect flight id");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.UPDATE_FLIGHT;
        }
        return page;
    }
}
