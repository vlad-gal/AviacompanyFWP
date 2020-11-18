package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.Status;
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
 * The class represents command to authorization user in system.
 * If user is present in system and hasn't status INACTIVE, authorization will be successful.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String page;
        AuthorizationData authorizationData = new AuthorizationData(login, password);
        if (UserValidator.isValidAuthorizationData(authorizationData)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> optionalUser = service.authorization(authorizationData);
                if (optionalUser.isPresent()) {
                    if (optionalUser.get().getStatus() != Status.INACTIVE) {
                        session.setAttribute(ParameterName.USER, optionalUser.get());
                        session.setAttribute(ParameterName.ROLES, User.Role.values());
                        session.setAttribute(ParameterName.STATUSES, Status.values());
                        page = PagePath.USER_ACCOUNT;
                    } else {
                        logger.log(Level.ERROR, "User inactive");
                        request.setAttribute(ParameterName.INACTIVE_USER_FLAG, true);
                        page = PagePath.AUTHORIZATION;
                    }
                } else {
                    logger.log(Level.ERROR, "User not found");
                    request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD_FLAG, true);
                    page = PagePath.AUTHORIZATION;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while authorization user", e);
                request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD_FLAG, true);
                page = PagePath.AUTHORIZATION;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect authorization data");
            request.setAttribute(ParameterName.AUTHORIZATION_DATA, authorizationData);
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.AUTHORIZATION;
        }
        return page;
    }
}
