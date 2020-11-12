package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import by.halatsevich.company.validator.CrewValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateCrewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateCrewCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Crew updatingCrew = (Crew) session.getAttribute(ParameterName.UPDATING_CREW);
        String numberOfPilots = request.getParameter(ParameterName.NUMBER_OF_PILOTS);
        String numberOfNavigators = request.getParameter(ParameterName.NUMBER_OF_NAVIGATORS);
        String numberOfRadioman = request.getParameter(ParameterName.NUMBER_OF_RADIOMAN);
        String numberOfStewardesses = request.getParameter(ParameterName.NUMBER_OF_STEWARDESSES);
        String status = request.getParameter(ParameterName.STATUS);

        String page;
        if (CrewValidator.isValidNumberOfPilots(numberOfPilots)
                && CrewValidator.isValidNumberOfNavigators(numberOfNavigators)
                && CrewValidator.isValidNumberOfRadioman(numberOfRadioman)
                && CrewValidator.isValidNumberOfStewardesses(numberOfStewardesses)
                && BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService crewService = factory.getCrewService();
            try {
                boolean isCrewUpdate = crewService.updateCrew(updatingCrew, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, status);
                if (isCrewUpdate) {
                    request.setAttribute(ParameterName.UPDATING_SUCCESSFUL_FLAG, true);
                    request.setAttribute(ParameterName.UPDATING_CREW, crewService.findCrewById(updatingCrew.getId()));
                    page = PagePath.UPDATE_CREW;
                } else {
                    logger.log(Level.ERROR, "Error while updating crew");
                    request.setAttribute(ParameterName.ERROR_UPDATE_CREW_FLAG, true);
                    request.setAttribute(ParameterName.UPDATING_FLIGHT, updatingCrew);
                    page = PagePath.UPDATE_CREW;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot updating crew", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            session.setAttribute(ParameterName.UPDATING_CREW, updatingCrew);
            page = PagePath.UPDATE_CREW;
        }
        return page;
    }
}
