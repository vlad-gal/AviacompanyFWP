package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ConfirmAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        String email = request.getParameter(ParameterName.EMAIL);
        String page;
        if (UserValidator.isValidEmail(email)){
            try {
                if (service.isEmailExist(email) && service.updateUserStatus(email, Status.ACTIVE)) {
                    page = PagePath.SUCCESSFUL_ACTIVATION;
                } else {
                    page = PagePath.REGISTRATION;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while updating users status");
                request.setAttribute(ParameterName.ERROR_MESSAGE,e);
                page = PagePath.ERROR;
            }
        } else {
            logger.log(Level.WARN, "Invalid email");
            page = PagePath.ERROR;
        }
        return page;
    }
}
