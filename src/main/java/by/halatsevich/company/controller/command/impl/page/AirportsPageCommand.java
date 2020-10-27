package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class AirportsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AirportsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        AirportService airportService = factory.getAirportService();
        String page;
        List<Airport> airportList;
        try {
            airportList = airportService.findAllAirports();
            request.setAttribute(ParameterName.AIRPORT_LIST, airportList);
            page = PagePath.AIRPORTS;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all airports", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
