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
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateAircraftCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAircraftCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Aircraft updatingAircraft = (Aircraft) session.getAttribute(ParameterName.UPDATING_AIRCRAFT);
        String aircraftType = request.getParameter(ParameterName.AIRCRAFT_TYPE);
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        if (AircraftValidator.isValidAircraftType(aircraftType)
                && BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AircraftService aircraftService = factory.getAircraftService();
            try {
                updatingAircraft.setAircraftType(Aircraft.AircraftType.valueOf(aircraftType.toUpperCase()));
                updatingAircraft.setStatus(Status.valueOf(status.toUpperCase()));
                boolean isUpdated = aircraftService.updateAircraft(updatingAircraft);
                if (isUpdated) {
                    request.setAttribute(ParameterName.UPDATING_AIRCRAFT, updatingAircraft);
                    request.setAttribute(ParameterName.UPDATE_AIRCRAFT_SUCCESSFUL_FLAG, true);
                    page = PagePath.UPDATE_AIRCRAFT;
                } else {
                    request.setAttribute(ParameterName.UPDATING_AIRCRAFT, updatingAircraft);
                    request.setAttribute(ParameterName.ERROR_UPDATE_AIRCRAFT_FLAG, true);
                    page = PagePath.UPDATE_AIRCRAFT;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot update aircraft", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.UPDATING_AIRCRAFT, updatingAircraft);
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.UPDATE_AIRCRAFT;
        }
        return page;
    }
}
