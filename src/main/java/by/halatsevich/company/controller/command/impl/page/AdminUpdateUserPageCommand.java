package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The class represents preparation for forwarding to the update user page by admin.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AdminUpdateUserPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminUpdateUserPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter(ParameterName.LOGIN);
        String page;
        if (UserValidator.isValidLogin(login)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService userService = factory.getUserService();
            try {
                Optional<User> userByLogin = userService.findUserByLogin(login);
                if (userByLogin.isPresent()) {
                    session.setAttribute(ParameterName.UPDATING_USER, userByLogin.get());
                    page = PagePath.UPDATE_USER;
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
