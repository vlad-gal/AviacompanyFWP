package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateFlightPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateFlightPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        CrewService crewService = factory.getCrewService();
        AirportService airportService = factory.getAirportService();
        AircraftService aircraftService = factory.getAircraftService();
        UserService userService = factory.getUserService();
        List<CrewDto> crews;
        List<Airport> airports;
        List<Aircraft> aircrafts;
        List<User> operators;
        String page;
        try {
            crews = crewService.findCrewsByStatus(Status.ACTIVE);
            airports = airportService.findAllAirports();
            aircrafts = aircraftService.findAllAircrafts();
            operators = userService.findUsersByRoleAndStatus(User.Role.OPERATOR,Status.ACTIVE);
            session.setAttribute(ParameterName.CREWS, crews);
            session.setAttribute(ParameterName.AIRPORTS, airports);
            session.setAttribute(ParameterName.AIRCRAFTS, aircrafts);
            session.setAttribute(ParameterName.OPERATORS, operators);
            page = PagePath.CREATE_FLIGHT;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all crews", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
