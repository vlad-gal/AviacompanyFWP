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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AirportDetailPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AirportDetailPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String airportId = request.getParameter(ParameterName.AIRPORT_ID);
        String page;
        if (BaseValidator.isValidId(airportId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AirportService airportService = factory.getAirportService();
            try {
                Airport airport = airportService.findAirportById(airportId);
                if (airport != null) {
                    request.setAttribute(ParameterName.AIRPORT, airport);
                    page = PagePath.DETAIL_PAGE;
                } else {
                    request.setAttribute(ParameterName.AIRPORT_NOT_FOUND_FLAG, true);
                    page = PagePath.DETAIL_PAGE;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding airport by id", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR;
            }
        } else {
            logger.log(Level.WARN, "Invalid airport id");
            page = PagePath.ERROR;
        }
        return page;
    }
}
