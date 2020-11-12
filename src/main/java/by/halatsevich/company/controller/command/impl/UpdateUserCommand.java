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

public class UpdateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String telephoneNumber = request.getParameter(ParameterName.TELEPHONE_NUMBER);
        String page;
        if (UserValidator.isValidName(firstName) && UserValidator.isValidName(lastName)
                && UserValidator.isValidTelephoneNumber(telephoneNumber)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setTelephoneNumber(Long.parseLong(telephoneNumber));
                boolean isUserUpdated = service.updateUser(user);
                if (isUserUpdated) {
                    request.setAttribute(ParameterName.UPDATING_SUCCESSFUL_FLAG, true);
                    page = PagePath.SETTING_PAGE;
                } else {
                    logger.log(Level.ERROR, "Cannot updating user");
                    request.setAttribute(ParameterName.USER, user);
                    request.setAttribute(ParameterName.ERROR_UPDATE_USER_FLAG, true);
                    page = PagePath.SETTING_PAGE;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot updating user", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            request.setAttribute(ParameterName.USER, user);
            page = PagePath.SETTING_PAGE;
        }
        return page;
    }
}
