package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.AircraftValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents command to create aircraft.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
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
                Aircraft aircraft = new Aircraft(tailNumber, aircraftName,
                        Aircraft.AircraftType.valueOf(aircraftType.toUpperCase()), Status.ACTIVE);
                boolean isAdded = aircraftService.addAircraft(aircraft);
                if (isAdded) {
                    request.setAttribute(ParameterName.CREATE_AIRCRAFT_SUCCESSFUL_FLAG, true);
                    page = PagePath.CREATE_AIRCRAFT;
                } else {
                    request.setAttribute(ParameterName.AIRCRAFT_TAIL_NUMBER, tailNumber);
                    request.setAttribute(ParameterName.AIRCRAFT_NAME, aircraftName);
                    request.setAttribute(ParameterName.AIRCRAFT_TYPE,
                            Aircraft.AircraftType.valueOf(aircraftType.toUpperCase()));
                    request.setAttribute(ParameterName.ERROR_CREATE_AIRCRAFT_FLAG, true);
                    page = PagePath.CREATE_AIRCRAFT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create aircraft", e);
                request.setAttribute(ParameterName.ERROR, e);
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