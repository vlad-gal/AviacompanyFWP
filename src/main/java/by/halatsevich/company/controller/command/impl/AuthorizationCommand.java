package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.controller.util.MessageManager;
import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.model.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);
    private static final String ERROR_LOGIN_PASSWORD_MESSAGE = "local.common.errorLoginPassword";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String lang = (String) request.getSession().getAttribute(ParameterName.SESSION_LANG_ATTRIBUTE);
        MessageManager messageManager = new MessageManager(lang);

        if (login.isEmpty() || password.isEmpty()) {
            request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD, messageManager.getMessage(ERROR_LOGIN_PASSWORD_MESSAGE));
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.AUTHORIZATION_PAGE);
            request.getRequestDispatcher(PagePath.AUTHORIZATION_PAGE).forward(request, response);
        }
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        try {
            User user = service.authorization(new AuthorizationData(login, password));
            // TODO: 17.10.2020 работа с куками
//            String rememberMe = request.getParameter("rememberUser");
//            logger.log(Level.INFO, rememberMe);
//            if (rememberMe.equals("on")){
//                Cookie cookie = new Cookie();
//                response.addCookie(new Cookie());
//            }
            request.getSession().setAttribute(ParameterName.USER_ID, user.getId());
            request.getSession().setAttribute(ParameterName.USER_FIRST_NAME, user.getUserData().getFirstName());
            request.getSession().setAttribute(ParameterName.USER_LAST_NAME, user.getUserData().getLastName());
            request.getSession().setAttribute(ParameterName.USER_EMAIL, user.getEmail());
            request.getSession().setAttribute(ParameterName.USER_TELEPHONE_NUMBER, user.getUserData().getTelephoneNumber());
            request.getSession().setAttribute(ParameterName.USER_LOGIN, user.getLogin());
            request.getSession().setAttribute(ParameterName.USER_ROLE, user.getRole().name().toLowerCase());
            request.getSession().setAttribute(ParameterName.USER_STATUS, user.getStatus());
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.USER_PAGE);
            response.sendRedirect(PagePath.USER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Incorrect login or password");
            request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD, messageManager.getMessage(ERROR_LOGIN_PASSWORD_MESSAGE));
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.AUTHORIZATION_PAGE);
            request.getRequestDispatcher(PagePath.AUTHORIZATION_PAGE).forward(request, response);
        }
    }
}
