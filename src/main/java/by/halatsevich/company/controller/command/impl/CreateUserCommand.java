package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.RegistrationData;
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
import java.util.Optional;

public class CreateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String email = request.getParameter(ParameterName.EMAIL);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String telephoneNumber = request.getParameter(ParameterName.TELEPHONE_NUMBER);
        String role = request.getParameter(ParameterName.ROLE);
        String page;
        RegistrationData registrationData = new RegistrationData(login, email, password, firstName, lastName, telephoneNumber,role, Status.ACTIVE.getStatusName());
        if (UserValidator.isValidRegistrationData(registrationData)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> userByEmail = service.findUserByEmail(email);
                Optional<User> userByLogin = service.findUserByLogin(login);
                if (!userByEmail.isPresent() && !userByLogin.isPresent()) {
                    boolean isUserRegistered = service.registration(registrationData);
                    if (isUserRegistered) {
                        request.setAttribute(ParameterName.REGISTRATION_SUCCESSFUL_FLAG,true);
                        page = PagePath.CREATE_USER;
                    } else {
                        logger.log(Level.ERROR, "Cannot register user");
                        request.setAttribute(ParameterName.ERROR_REGISTER_USER_FLAG, true);
                        request.setAttribute(ParameterName.REGISTRATION_DATA, registrationData);
                        page = PagePath.CREATE_USER;
                    }
                } else {
                    logger.log(Level.ERROR, "User already exist");
                    request.setAttribute(ParameterName.USER_ALREADY_EXIST_FLAG, true);
                    request.setAttribute(ParameterName.REGISTRATION_DATA, registrationData);
                    page = PagePath.CREATE_USER;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while registering user", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid validation");
            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
            request.setAttribute(ParameterName.REGISTRATION_DATA, registrationData);
            page = PagePath.CREATE_USER;
        }
        return page;
    }
}
