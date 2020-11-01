package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ResetPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ResetPasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        String page;
        if (UserValidator.isValidPassword(password)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> user = service.findUserByEmail(email);
                if (user.isPresent()) {
                    boolean isPasswordUpdated = service.updatePassword(user.get(), password);
                    if (isPasswordUpdated) {
                        page = PagePath.SUCCESSFUL_PASSWORD_RESET;
                    } else {
                        logger.log(Level.WARN, "Password won't update");
                        request.setAttribute(ParameterName.INCORRECT_PASSWORD_FLAG, true);
                        page = PagePath.RESET_PASSWORD;
                    }
                } else {
                    logger.log(Level.WARN, "Email not exist");
                    request.setAttribute("emailNotExistFlag", true);
                    page = PagePath.FORGOT_PASSWORD;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while updating users status");
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.WARN, "Incorrect password");
            request.setAttribute(ParameterName.INCORRECT_PASSWORD_FLAG, true);
            page = PagePath.RESET_PASSWORD;
        }
        return page;
    }
}
