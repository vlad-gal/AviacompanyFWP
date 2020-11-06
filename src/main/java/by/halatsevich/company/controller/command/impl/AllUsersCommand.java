package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        try {
            List<User> userList = service.findUsersByStatus(status);
            request.setAttribute(ParameterName.ALL_USERS_LIST, userList);
            page = PagePath.USER_ACCOUNT;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all users by status", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}