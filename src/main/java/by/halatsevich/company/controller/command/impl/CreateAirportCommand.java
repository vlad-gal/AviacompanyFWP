package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.AirportValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateAirportCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateAirportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
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
                boolean isAdded = airportService.addAirport(airportName, city, country);
                if (isAdded) {
                    request.setAttribute(ParameterName.AIRPORT_NAME, airportName);
                    request.setAttribute(ParameterName.AIRPORT_CITY, city);
                    request.setAttribute(ParameterName.AIRPORT_COUNTRY, country);
                    request.setAttribute(ParameterName.CREATE_AIRPORT_SUCCESSFUL_FLAG, true);
                    page = PagePath.CREATE_AIRPORT;
                } else {
                    request.setAttribute(ParameterName.AIRPORT_NAME, airportName);
                    request.setAttribute(ParameterName.AIRPORT_CITY, city);
                    request.setAttribute(ParameterName.AIRPORT_COUNTRY, country);
                    request.setAttribute(ParameterName.ERROR_CREATE_AIRPORT_FLAG, true);
                    page = PagePath.CREATE_AIRPORT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create airport", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CREATE_AIRPORT;
        }
        return page;
    }
}
