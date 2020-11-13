package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The class represents preparation for adding a user to a crew.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AddUserIntoCrewPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddUserIntoCrewPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterName.LOGIN);
        HttpSession session = request.getSession();
        String page;
        if (UserValidator.isValidLogin(login)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService userService = factory.getUserService();
            CrewService crewService = factory.getCrewService();
            try {
                Optional<User> userByLogin = userService.findUserByLogin(login);
                if (userByLogin.isPresent()) {
                    List<Crew> crews = crewService.findCrewsByStatus(Status.ACTIVE.getStatusName());
                    session.setAttribute(ParameterName.ADDING_USER, userByLogin.get());
                    session.setAttribute(ParameterName.CREW_LIST, crews);
                    page = PagePath.ADD_USER_INTO_CREW;
                } else {
                    logger.log(Level.ERROR, "User not found");
                    page = PagePath.ERROR_404;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding user by login", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect user login");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
