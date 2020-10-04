package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterMessage;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.service.ServiceFactory;
import by.halatsevich.company.service.UserService;
import by.halatsevich.company.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        if (login.isEmpty() || password.isEmpty()) {
            logger.log(Level.INFO, "Incorrect login or password");
            request.getSession().setAttribute(ParameterName.ERROR_LOGIN_PASS, ParameterMessage.ERROR_LOGIN_PASS_MESSAGE);
            response.sendRedirect(PagePath.AUTHORIZATION_PAGE);
        } else {
            AuthorizationData data = new AuthorizationData();
            data.setLogin(login);
            data.setPassword(password);
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                User user = service.authorization(data);
                defineMainPage(request, response, user);
            } catch (ServiceException e) {
                logger.log(Level.INFO, "Incorrect login or password", e);
                request.getSession().setAttribute(ParameterName.ERROR_LOGIN_PASS, ParameterMessage.ERROR_LOGIN_PASS_MESSAGE);
                response.sendRedirect(PagePath.AUTHORIZATION_PAGE);
            }
        }
    }

    private void defineMainPage(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        switch (user.getRole()){
            case ADMIN: {
                request.getSession().setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
                request.getSession().setAttribute(ParameterName.STATUS, user.getStatus().toString().toLowerCase());
                response.sendRedirect(PagePath.ADMIN_MAIN_PAGE);
                break;
            }
            case PILOT:
            case NAVIGATOR:
            case RADIOMAN:
            case STEWARDESS:{
                request.getSession().setAttribute(ParameterName.FIRST_NAME, user.getUserData().getFirstName());
                request.getSession().setAttribute(ParameterName.LAST_NAME, user.getUserData().getLastName());
                request.getSession().setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
                response.sendRedirect(PagePath.PERSONAL_MAIN_PAGE);
                break;
            }
            case DISPATCHER:{
                request.getSession().setAttribute(ParameterName.FIRST_NAME, user.getUserData().getFirstName());
                request.getSession().setAttribute(ParameterName.LAST_NAME, user.getUserData().getLastName());
                request.getSession().setAttribute(ParameterName.TELEPHONE, user.getUserData().getTelephoneNumber());
                request.getSession().setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
                response.sendRedirect(PagePath.DISPATCHER_MAIN_PAGE);
                break;
            }
            case OPERATOR:{
                request.getSession().setAttribute(ParameterName.FIRST_NAME, user.getUserData().getFirstName());
                request.getSession().setAttribute(ParameterName.LAST_NAME, user.getUserData().getLastName());
                request.getSession().setAttribute(ParameterName.TELEPHONE, user.getUserData().getTelephoneNumber());
                request.getSession().setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
                response.sendRedirect(PagePath.OPERATOR_MAIN_PAGE);
                break;
            }
            case DEFAULT:{
                request.getSession().setAttribute(ParameterName.FIRST_NAME, user.getUserData().getFirstName());
                request.getSession().setAttribute(ParameterName.LAST_NAME, user.getUserData().getLastName());
                request.getSession().setAttribute(ParameterName.TELEPHONE, user.getUserData().getTelephoneNumber());
                request.getSession().setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
                request.getSession().setAttribute(ParameterName.STATUS, user.getStatus().toString().toLowerCase());
                response.sendRedirect(PagePath.DEFAULT_MAIN_PAGE);
                break;
            }
        }
    }
}
