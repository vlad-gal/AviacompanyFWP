package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Status;
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

public class AdminUpdateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminUpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User updatingUser = (User) session.getAttribute(ParameterName.UPDATING_USER);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String telephoneNumber = request.getParameter(ParameterName.TELEPHONE_NUMBER);
        String role = request.getParameter(ParameterName.ROLE);
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        if (UserValidator.isValidName(firstName) && UserValidator.isValidName(lastName)
                && UserValidator.isValidTelephoneNumber(telephoneNumber)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                updatingUser.setFirstName(firstName);
                updatingUser.setLastName(lastName);
                updatingUser.setTelephoneNumber(Long.parseLong(telephoneNumber));
                updatingUser.setRole(User.Role.valueOf(role));
                updatingUser.setStatus(Status.valueOf(status));
                boolean isUserUpdated = service.updateUser(updatingUser);
                if (isUserUpdated) {
                    request.setAttribute(ParameterName.UPDATING_SUCCESSFUL_FLAG, true);
                    session.setAttribute(ParameterName.UPDATING_USER,updatingUser);
                    page = PagePath.UPDATE_USER;
                } else {
                    logger.log(Level.WARN, "Cannot updating user");
                    request.setAttribute(ParameterName.FIRST_NAME, firstName);
                    session.setAttribute(ParameterName.UPDATING_USER,updatingUser);
                    page = PagePath.UPDATE_USER;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot updating user", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            session.setAttribute(ParameterName.UPDATING_USER,updatingUser);
            page = PagePath.UPDATE_USER;
        }
        return page;
    }
}
