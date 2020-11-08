package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;
import by.halatsevich.company.util.mail.MailUtil;
import by.halatsevich.company.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ForgotPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter(ParameterName.EMAIL);
        String page;
        if (UserValidator.isValidEmail(email)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService service = factory.getUserService();
            try {
                Optional<User> user = service.findUserByEmail(email);
                if (user.isPresent()) {
                    MailUtil.getInstance().sendMessage((String) session.getAttribute(ParameterName.LANG),
                            email, request.getRequestURL().toString(), MailUtil.MailType.RESET);
                    request.setAttribute(ParameterName.RESET_LINK_SENT_SUCCESSFUL_FLAG, true);
                    page = PagePath.SUCCESSFUL_MESSAGE;
                } else {
                    logger.log(Level.ERROR, "Email not exist");
                    request.setAttribute(ParameterName.EMAIL_NOT_EXIST_FLAG, true);
                    page = PagePath.FORGOT_PASSWORD;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while checking email", e);
                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Incorrect email");
            request.setAttribute(ParameterName.INCORRECT_EMAIL_FLAG, true);
            page = PagePath.FORGOT_PASSWORD;
        }
        return page;
    }
}
