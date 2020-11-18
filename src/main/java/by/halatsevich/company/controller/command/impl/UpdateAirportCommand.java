package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Airport;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.AirportValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents command to update airport.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateAirportCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAirportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Airport updatingAirport = (Airport) session.getAttribute(ParameterName.UPDATING_AIRPORT);
        String airportName = request.getParameter(ParameterName.AIRPORT_NAME);
        String city = request.getParameter(ParameterName.AIRPORT_CITY);
        String country = request.getParameter(ParameterName.AIRPORT_COUNTRY);
        String page;
        if (AirportValidator.isValidAirportName(airportName)
                && AirportValidator.isValidCityOrCountry(city)
                && AirportValidator.isValidCityOrCountry(country)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AirportService airportService = factory.getAirportService();
            try {
                updatingAirport.setAirportName(airportName);
                updatingAirport.setCity(city);
                updatingAirport.setCountry(country);
                boolean isUpdated = airportService.updateAirport(updatingAirport);
                if (isUpdated) {
                    request.setAttribute(ParameterName.UPDATING_AIRPORT, updatingAirport);
                    request.setAttribute(ParameterName.UPDATE_AIRPORT_SUCCESSFUL_FLAG, true);
                    page = PagePath.UPDATE_AIRPORT;
                } else {
                    request.setAttribute(ParameterName.UPDATING_AIRPORT, updatingAirport);
                    request.setAttribute(ParameterName.ERROR_UPDATE_AIRPORT_FLAG, true);
                    page = PagePath.UPDATE_AIRPORT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot update airport", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.UPDATING_AIRPORT, updatingAirport);
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.UPDATE_AIRPORT;
        }
        return page;
    }
}
