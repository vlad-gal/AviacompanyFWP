package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserIntoCrewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddUserIntoCrewCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User addingUser = (User) session.getAttribute(ParameterName.ADDING_USER);
        String crewId = request.getParameter(ParameterName.CREW);
        String page;
        if (BaseValidator.isValidId(crewId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService crewService = factory.getCrewService();
            try {
                Crew crew = crewService.findCrewById(Integer.parseInt(crewId));
                int availablePlacesInCrew = crewService.countAvailablePlacesInCrew(crew, addingUser);
                if (availablePlacesInCrew > 0) {
                    crewService.addUserIntoCrew(addingUser, crewId);
                    request.setAttribute(ParameterName.ADD_USER_INTO_CREW_SUCCESSFUL_FLAG, true);
                    request.setAttribute(ParameterName.CREW, crew);
                    page = PagePath.ADD_USER_INTO_CREW;
                } else {
                    request.setAttribute(ParameterName.AVAILABLE_PLACES_IN_CREW, availablePlacesInCrew);
                    request.setAttribute(ParameterName.AVAILABLE_PLACES_IN_CREW_FLAG, true);
                    request.setAttribute(ParameterName.ERROR_ADD_USER_INTO_CREW_FLAG, true);
                    request.setAttribute(ParameterName.CREW, crew);
                    page = PagePath.ADD_USER_INTO_CREW;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while adding user into crew", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.ADD_USER_INTO_CREW;
        }
        return page;
    }
}
