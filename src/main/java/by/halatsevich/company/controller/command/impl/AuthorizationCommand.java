package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        HttpSession session = request.getSession();
        String page;
        AuthorizationData authorizationData = new AuthorizationData(login, password);
        if (UserValidator.isValidAuthorizationData(authorizationData)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> optionalUser = service.authorization(authorizationData);
                if (optionalUser.isPresent()){
                    if (optionalUser.get().getStatus() == Status.ACTIVE){
                        session.setAttribute(ParameterName.USER, optionalUser.get());
                        page = PagePath.USER_ACCOUNT;
                    } else {
                        request.setAttribute(ParameterName.INACTIVE_USER_FLAG, true);
                        page = PagePath.AUTHORIZATION;
                    }
                } else {
                    request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD_FLAG, true);
                    page = PagePath.AUTHORIZATION;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Incorrect login or password",e);
                request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD_FLAG, true);
                page = PagePath.AUTHORIZATION;
            }
        } else {
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            page = PagePath.AUTHORIZATION;
        }
        return page;
    }
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter(ParameterName.LOGIN);
//        String password = request.getParameter(ParameterName.PASSWORD);
//        String lang = (String) request.getSession().getAttribute(ParameterName.LANG);
//        MessageManager messageManager = new MessageManager(lang);
//
//        if (login.isEmpty() || password.isEmpty()) {
//            request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD, messageManager.getMessage(ERROR_LOGIN_PASSWORD_MESSAGE));
//            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.AUTHORIZATION);
//            request.getRequestDispatcher(PagePath.AUTHORIZATION).forward(request, response);
//        }
//        ServiceFactory factory = ServiceFactory.getInstance();
//        UserService service = factory.getUserService();
//        try {
//            User user = service.authorization(new AuthorizationData(login, password));
//            request.getSession().setAttribute(ParameterName.USER_ID, user.getId());
//            request.getSession().setAttribute(ParameterName.USER_FIRST_NAME, user.getFirstName());
//            request.getSession().setAttribute(ParameterName.USER_LAST_NAME, user.getLastName());
//            request.getSession().setAttribute(ParameterName.USER_EMAIL, user.getEmail());
//            request.getSession().setAttribute(ParameterName.USER_TELEPHONE_NUMBER, user.getTelephoneNumber());
//            request.getSession().setAttribute(ParameterName.USER_LOGIN, user.getLogin());
//            request.getSession().setAttribute(ParameterName.USER_ROLE, user.getRole().name().toLowerCase());
//            request.getSession().setAttribute(ParameterName.USER_STATUS, user.getStatus());
//            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.USER_ACCOUNT);
//            response.sendRedirect(PagePath.USER_ACCOUNT);
//        } catch (ServiceException e) {
//            logger.log(Level.ERROR, "Incorrect login or password");
//            request.setAttribute(ParameterName.ERROR_LOGIN_PASSWORD, messageManager.getMessage(ERROR_LOGIN_PASSWORD_MESSAGE));
//            request.getSession().setAttribute(ParameterName.CURRENT_PAGE, PagePath.AUTHORIZATION);
//            request.getRequestDispatcher(PagePath.AUTHORIZATION).forward(request, response);
//        }
//    }


}
