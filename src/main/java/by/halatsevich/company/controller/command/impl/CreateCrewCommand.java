package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.CrewValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The class represents creating crew command.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CreateCrewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateCrewCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User dispatcher = (User) session.getAttribute(ParameterName.USER);
        String crewName = request.getParameter(ParameterName.CREW_NAME);
        String numberOfPilots = request.getParameter(ParameterName.NUMBER_OF_PILOTS);
        String numberOfNavigators = request.getParameter(ParameterName.NUMBER_OF_NAVIGATORS);
        String numberOfRadioman = request.getParameter(ParameterName.NUMBER_OF_RADIOMAN);
        String numberOfStewardesses = request.getParameter(ParameterName.NUMBER_OF_STEWARDESSES);
        String page;
        if (CrewValidator.isValidCrewName(crewName)
                && CrewValidator.isValidNumberOfPilots(numberOfPilots)
                && CrewValidator.isValidNumberOfNavigators(numberOfNavigators)
                && CrewValidator.isValidNumberOfRadioman(numberOfRadioman)
                && CrewValidator.isValidNumberOfStewardesses(numberOfStewardesses)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService crewService = factory.getCrewService();
            try {
                boolean isCrewCreate = crewService.addCrew(dispatcher, crewName, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, Status.ACTIVE);

                if (isCrewCreate) {
                    request.setAttribute(ParameterName.CREATE_CREW_SUCCESSFUL_FLAG, true);
                    Optional<CrewDto> optionalCrewDto = crewService.findByCrewName(crewName);
                    if (optionalCrewDto.isPresent()) {
                        CrewDto crewDto = optionalCrewDto.get();
                        request.setAttribute(ParameterName.CREW_DTO, crewDto);
                        page = PagePath.CREATE_CREW;
                    } else {
                        logger.log(Level.ERROR, "Crew with that name not found");
                        request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
                        page = PagePath.CREATE_CREW;
                    }
                } else {
                    logger.log(Level.ERROR, "Error while creating crew");
                    request.setAttribute(ParameterName.ERROR_CREATE_CREW_FLAG, true);
                    page = PagePath.CREATE_CREW;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot create crew", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CREATE_CREW;
        }
        return page;
    }
}
