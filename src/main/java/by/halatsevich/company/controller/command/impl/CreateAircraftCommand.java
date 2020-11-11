package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.AircraftValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateAircraftCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateAircraftCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String tailNumber = request.getParameter(ParameterName.AIRCRAFT_TAIL_NUMBER);
        String aircraftName = request.getParameter(ParameterName.AIRCRAFT_NAME);
        String aircraftType = request.getParameter(ParameterName.AIRCRAFT_TYPE);
        String page;
        if (AircraftValidator.isValidTailNumber(tailNumber)
                && AircraftValidator.isValidAircraftName(aircraftName)
                && AircraftValidator.isValidAircraftType(aircraftType)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AircraftService aircraftService = factory.getAircraftService();
            try {
                boolean isAdded = aircraftService.addAircraft(tailNumber, aircraftName, aircraftType, Status.ACTIVE);
                if (isAdded) {
                    request.setAttribute(ParameterName.AIRCRAFT_TAIL_NUMBER, tailNumber);
                    request.setAttribute(ParameterName.AIRCRAFT_NAME, aircraftName);
                    request.setAttribute(ParameterName.AIRCRAFT_TYPE, Aircraft.AircraftType.valueOf(aircraftType.toUpperCase()));
                    request.setAttribute(ParameterName.CREATE_AIRCRAFT_SUCCESSFUL_FLAG, true);
                    page = PagePath.CREATE_AIRCRAFT;
                } else {
                    request.setAttribute(ParameterName.AIRCRAFT_TAIL_NUMBER, tailNumber);
                    request.setAttribute(ParameterName.AIRCRAFT_NAME, aircraftName);
                    request.setAttribute(ParameterName.AIRCRAFT_TYPE, Aircraft.AircraftType.valueOf(aircraftType.toUpperCase()));
                    request.setAttribute(ParameterName.ERROR_CREATE_AIRCRAFT_FLAG, true);
                    page = PagePath.CREATE_AIRCRAFT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create aircraft", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CREATE_AIRCRAFT;
        }
        return page;
    }
}
