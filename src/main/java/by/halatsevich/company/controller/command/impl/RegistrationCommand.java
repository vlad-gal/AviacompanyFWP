package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.RegistrationData;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.util.mail.MailUtil;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String confirmPassword = request.getParameter(ParameterName.CONFIRM_PASSWORD);
        String email = request.getParameter(ParameterName.EMAIL);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String telephoneNumber = request.getParameter(ParameterName.TELEPHONE_NUMBER);
        HttpSession session = request.getSession();
        String page;
        RegistrationData registrationData = new RegistrationData(login, email, password, firstName, lastName, telephoneNumber);
        if (UserValidator.isValidRegistrationData(registrationData) && password.equals(confirmPassword)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                if (!service.isUserExist(login, email)) {
                    boolean isUserRegistered = service.registration(registrationData);
                    if (isUserRegistered) {
                        MailUtil.getInstance().sendActivationLink((String) session.getAttribute(ParameterName.LANG), email, request.getRequestURL().toString());
                        page = PagePath.SUCCESSFUL_REGISTRATION;
                    } else {
                        logger.log(Level.WARN, "Cannot register user");
                        request.setAttribute(ParameterName.ERROR_REGISTER_USER_FLAG, true);
                        page = PagePath.REGISTRATION;
                    }
                } else {
                    logger.log(Level.WARN, "User already exist");
                    request.setAttribute(ParameterName.USER_EXIST_FLAG, true);
                    page = PagePath.REGISTRATION;
                }

            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Cannot register user", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR;
            }
        } else {
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            request.setAttribute(ParameterName.REGISTRATION_DATA, registrationData);
            page = PagePath.REGISTRATION;
        }
        return page;
    }
}
