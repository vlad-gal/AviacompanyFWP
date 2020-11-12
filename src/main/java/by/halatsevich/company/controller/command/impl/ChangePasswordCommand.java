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
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String password = request.getParameter(ParameterName.PASSWORD);
        String confirmPassword = request.getParameter(ParameterName.CONFIRM_PASSWORD);
        String page;
        if (UserValidator.isValidPassword(password) && password.equals(confirmPassword)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                boolean isPasswordUpdated = service.updatePassword(user, password);
                if (isPasswordUpdated) {
                    request.setAttribute(ParameterName.UPDATING_SUCCESSFUL_FLAG, true);
                    page = PagePath.CHANGE_PASSWORD;
                } else {
                    logger.log(Level.ERROR, "Cannot updating password");
                    request.setAttribute(ParameterName.ERROR_UPDATE_PASSWORD_FLAG, true);
                    page = PagePath.CHANGE_PASSWORD;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while updating password", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid password");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.CHANGE_PASSWORD;
        }
        return page;
    }
}
