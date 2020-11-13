package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The class represents preparation for updating aircraft.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateAircraftPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAircraftPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String aircraftId = request.getParameter(ParameterName.AIRCRAFT_ID);
        HttpSession session = request.getSession();
        String page;
        if (BaseValidator.isValidId(aircraftId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AircraftService aircraftService = factory.getAircraftService();
            try {
                Optional<Aircraft> aircraft = aircraftService.findAircraftById(aircraftId);
                if (aircraft.isPresent()) {
                    session.setAttribute(ParameterName.UPDATING_AIRCRAFT, aircraft.get());
                    session.setAttribute(ParameterName.AIRCRAFT_TYPES, Aircraft.AircraftType.values());
                    page = PagePath.UPDATE_AIRCRAFT;
                } else {
                    logger.log(Level.ERROR, "Updating aircraft not found");
                    page = PagePath.ERROR_404;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding updating aircraft by id", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect aircraft id");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
