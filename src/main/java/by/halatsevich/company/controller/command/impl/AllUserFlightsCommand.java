package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.entity.User;
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

public class AllUserFlightsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllUserFlightsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String status = request.getParameter(ParameterName.STATUS);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String page;
        if (BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            FlightService service = factory.getFlightService();
            try {
                List<Flight> crews = service.findUsersFlightsByStatus(user, status);
                logger.log(Level.ERROR, crews);
                session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
                session.setAttribute(ParameterName.FLIGHT_LIST, crews);
                request.setAttribute(ParameterName.SHOW_FLIGHTS_FLAG, true);
                page = PagePath.USER_ACCOUNT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding all users flights by status", e);
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
