package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents preparation for updating crew.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UpdateCrewPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateCrewPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String crewId = request.getParameter(ParameterName.CREW_ID);
        HttpSession session = request.getSession();
        String page;
        if (BaseValidator.isValidId(crewId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService crewService = factory.getCrewService();
            try {
                Crew crew = crewService.findCrewById(Integer.parseInt(crewId));
                session.setAttribute(ParameterName.UPDATING_CREW, crew);
                page = PagePath.UPDATE_CREW;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding crew by id", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect crew id");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
