package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The class represents preparation for updating airport.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateAirportPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAirportPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String airportId = request.getParameter(ParameterName.AIRPORT_ID);
        HttpSession session = request.getSession();
        String page;
        if (BaseValidator.isValidId(airportId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AirportService airportService = factory.getAirportService();
            try {
                Optional<Airport> airport = airportService.findAirportById(airportId);
                if (airport.isPresent()) {
                    session.setAttribute(ParameterName.UPDATING_AIRPORT, airport.get());
                    page = PagePath.UPDATE_AIRPORT;
                } else {
                    logger.log(Level.ERROR, "Updating airport not found");
                    page = PagePath.ERROR_404;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding updating airport by id", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect airport id");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
