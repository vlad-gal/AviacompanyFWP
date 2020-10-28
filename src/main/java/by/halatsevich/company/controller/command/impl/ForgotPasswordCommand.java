package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.util.mail.MailUtil;
import by.halatsevich.company.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ForgotPasswordCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String email = request.getParameter(ParameterName.EMAIL);
        String page;
        if (UserValidator.isValidEmail(email)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                if (service.isEmailExist(email)) {
                    MailUtil.getInstance().sendResetPasswordLink((String) request.getAttribute(ParameterName.LANG), email, request.getRequestURL().toString());
                    page = PagePath.RESET_PASSWORD_LINK_SENT;
                } else {
                    request.setAttribute("emailNotExistFlag", true);
                    page = PagePath.FORGOT_PASSWORD;
                }
            } catch (ServiceException e) {
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR;
            }
        } else {
            request.setAttribute("incorrectEmailFlag",true);
            page = PagePath.FORGOT_PASSWORD;
        }
        return page;
    }
}
