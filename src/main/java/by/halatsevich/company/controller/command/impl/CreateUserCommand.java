//package by.halatsevich.company.controller.command.impl;
//
//import by.halatsevich.company.controller.PagePath;
//import by.halatsevich.company.controller.ParameterName;
//import by.halatsevich.company.controller.command.Command;
//import by.halatsevich.company.model.entity.RegistrationData;
//import by.halatsevich.company.model.exception.ServiceException;
//import by.halatsevich.company.model.service.ServiceFactory;
//import by.halatsevich.company.model.service.UserService;
//import by.halatsevich.company.util.mail.MailUtil;
//import by.halatsevich.company.validator.UserValidator;
//import org.apache.logging.log4j.Level;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class CreateUserCommand implements Command {
//    @Override
//    public String execute(HttpServletRequest request) {
//        String login = request.getParameter(ParameterName.LOGIN);
//        String password = request.getParameter(ParameterName.PASSWORD);
//        String email = request.getParameter(ParameterName.EMAIL);
//        String firstName = request.getParameter(ParameterName.FIRST_NAME);
//        String lastName = request.getParameter(ParameterName.LAST_NAME);
//        String telephoneNumber = request.getParameter(ParameterName.TELEPHONE_NUMBER);
//        String role = request.getParameter(ParameterName.ROLE);
//
//        String page;
//        RegistrationData registrationData = new RegistrationData(login, email, password, firstName, lastName, telephoneNumber);
//        if (UserValidator.isValidRegistrationData(registrationData)) {
//            ServiceFactory factory = ServiceFactory.getInstance();
//            UserService service = factory.getUserService();
//            try {
//                if (!service.isUserExist(login, email)) {
//                    boolean isUserRegistered = service.registration(registrationData);
//                    if (isUserRegistered) {
//                        service.updateUserRole(email,role);
//                        page = PagePath.SUCCESSFUL_REGISTRATION;
//                    } else {
//                        logger.log(Level.WARN, "Cannot register user");
//                        request.setAttribute(ParameterName.ERROR_REGISTER_USER_FLAG, true);
//                        page = PagePath.REGISTRATION;
//                    }
//                } else {
//                    logger.log(Level.WARN, "User already exist");
//                    request.setAttribute(ParameterName.USER_ALREADY_EXIST_FLAG, true);
//                    page = PagePath.REGISTRATION;
//                }
//
//            } catch (ServiceException e) {
//                logger.log(Level.ERROR, "Cannot register user", e);
//                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
//                page = PagePath.ERROR;
//            }
//        } else {
//            request.setAttribute(ParameterName.ERROR_VALIDATION_FLAG, true);
//            request.setAttribute(ParameterName.REGISTRATION_DATA, registrationData);
//            page = PagePath.CREATE_USER;
//        }
//        return page;
//    }
//}
