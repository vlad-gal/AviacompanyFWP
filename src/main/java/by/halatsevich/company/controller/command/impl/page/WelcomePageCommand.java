package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.FlightService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class WelcomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(WelcomePageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        FlightService flightService = factory.getFlightService();
        List<Flight> flightList;
        try {
            flightList = flightService.findAllActualFlights();
            request.setAttribute(ParameterName.FLIGHT_LIST, flightList);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all flights", e);
            request.setAttribute(ParameterName.ERROR_GET_DATA_FROM_DB_FLAG, true);
        }
        return PagePath.WELCOME;
    }
}
