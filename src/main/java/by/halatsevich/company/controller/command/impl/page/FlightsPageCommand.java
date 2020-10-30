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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FlightsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FlightsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServiceFactory factory = ServiceFactory.getInstance();
        FlightService flightService = factory.getFlightService();
        String page;
        List<Flight> flightList;
        try {
            flightList = flightService.findAllActualFlights();
            request.setAttribute(ParameterName.FLIGHT_LIST, flightList);
            page = PagePath.FLIGHTS;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all actual flights", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
