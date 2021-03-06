package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
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
import java.util.Optional;

/**
 * The class represents command to confirm user's account by sending activation link to email.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class ConfirmAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(ParameterName.EMAIL);
        String page;
        if (UserValidator.isValidEmail(email)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> optionalUser = service.findUserByEmail(email);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setStatus(Status.ACTIVE);
                    if (service.updateUser(user)) {
                        request.setAttribute(ParameterName.ACTIVATION_SUCCESSFUL_FLAG, true);
                        page = PagePath.SUCCESSFUL_MESSAGE;
                    } else {
                        logger.log(Level.ERROR, "User not activated");
                        page = PagePath.ERROR_404;
                    }
                } else {
                    logger.log(Level.ERROR, "User not found");
                    page = PagePath.ERROR_404;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while updating users status", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.WARN, "Invalid email");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
