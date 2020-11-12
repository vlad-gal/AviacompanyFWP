package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class WelcomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(WelcomePageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        FlightService flightService = factory.getFlightService();
        List<Flight> flightList;
        String page;
        try {
            flightList = flightService.findFlightsByStatus(Status.ACTIVE.getStatusName());
            session.setAttribute(ParameterName.FLIGHT_LIST, flightList);
            page = PagePath.WELCOME;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all actual flights", e);
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
