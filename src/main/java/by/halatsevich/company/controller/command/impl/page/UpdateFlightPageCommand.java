package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateFlightPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateFlightPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String flightId = request.getParameter(ParameterName.FLIGHT_ID);
        HttpSession session = request.getSession();
        String page;
        if (BaseValidator.isValidId(flightId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService flightService = factory.getFlightService();
            try {
                Flight flight = flightService.findFlightById(flightId);
                session.setAttribute(ParameterName.UPDATING_FLIGHT, flight);
                page = PagePath.UPDATE_FLIGHT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding flight by id", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect flight id");
            request.setAttribute(ParameterName.INCORRECT_ID_FLAG, true);
            page = PagePath.UPDATE_FLIGHT;
        }
        return page;
    }
}
