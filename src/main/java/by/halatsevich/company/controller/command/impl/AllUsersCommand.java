package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.BaseValidator;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents command to show all users on user account.
 * This command find all users based on their status and role.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String status = request.getParameter(ParameterName.STATUS);
        String role = request.getParameter(ParameterName.ROLE);
        String page;
        if (BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                List<User> userList;
                if (role != null && UserValidator.isValidRole(role)) {
                    userList = service.findUsersByRoleAndStatus(role, status);
                } else {
                    userList = service.findUsersByStatus(status);
                }
                session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
                session.setAttribute(ParameterName.ALL_USERS_LIST, userList);
                request.setAttribute(ParameterName.SHOW_USERS_FLAG, true);
                page = PagePath.USER_ACCOUNT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding all users by status", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid role or status");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}
