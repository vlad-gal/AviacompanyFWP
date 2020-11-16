package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Flight;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents showing all flights command.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AllFlightsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllFlightsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        if (BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService service = factory.getFlightService();
            try {
                List<Flight> flightList = service.findFlightsByStatus(status);
                session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
                session.setAttribute(ParameterName.FLIGHT_LIST, flightList);
                request.setAttribute(ParameterName.SHOW_FLIGHTS_FLAG, true);
                page = PagePath.USER_ACCOUNT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding all flights by status", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid status");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
