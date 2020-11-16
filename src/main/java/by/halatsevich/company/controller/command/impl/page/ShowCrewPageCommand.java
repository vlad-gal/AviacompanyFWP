package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Crew;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class represents preparation for showing crew on page.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class ShowCrewPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowCrewPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String crewId = request.getParameter(ParameterName.CREW_ID);
        String page;
        if (BaseValidator.isValidId(crewId)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService crewService = factory.getCrewService();
            try {
                Crew crew = crewService.findCrewById(Integer.parseInt(crewId));
                List<User> pilots = crew.getStaff()
                        .stream().filter(user -> user.getRole() == User.Role.PILOT).collect(Collectors.toList());
                List<User> navigators = crew.getStaff()
                        .stream().filter(user -> user.getRole() == User.Role.NAVIGATOR).collect(Collectors.toList());
                List<User> radioman = crew.getStaff()
                        .stream().filter(user -> user.getRole() == User.Role.RADIOMAN).collect(Collectors.toList());
                List<User> stewardesses = crew.getStaff()
                        .stream().filter(user -> user.getRole() == User.Role.STEWARDESS).collect(Collectors.toList());
                request.setAttribute(ParameterName.DISPATCHER, crew.getDispatcher());
                request.setAttribute(ParameterName.PILOTS, pilots);
                request.setAttribute(ParameterName.NAVIGATORS, navigators);
                request.setAttribute(ParameterName.RADIOMANS, radioman);
                request.setAttribute(ParameterName.STEWARDESSES, stewardesses);
                page = PagePath.SHOW_CREW;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding crew by id", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect crew id");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
