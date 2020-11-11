package by.halatsevich.company.controller.command.impl;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllAirportsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllAirportsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        ServiceFactory factory = ServiceFactory.getInstance();
        AirportService airportService = factory.getAirportService();
        try {
            List<Airport> airports = airportService.findAllAirports();
            session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
            session.setAttribute(ParameterName.AIRPORT_LIST, airports);
            request.setAttribute(ParameterName.SHOW_AIRPORTS_FLAG, true);
            page = PagePath.USER_ACCOUNT;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all airports", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
