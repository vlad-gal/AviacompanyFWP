package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents preparation for showing airports on page.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AirportsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AirportsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        AirportService airportService = factory.getAirportService();
        String page;
        List<Airport> airportList;
        try {
            airportList = airportService.findAllAirports();
            session.setAttribute(ParameterName.AIRPORT_LIST, airportList);
            session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
            page = PagePath.AIRPORTS;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all airports", e);
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
