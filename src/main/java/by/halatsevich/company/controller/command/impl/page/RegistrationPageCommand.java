package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents preparation for registration.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class RegistrationPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(ParameterName.ROLES, User.Role.values());
        return PagePath.REGISTRATION;
    }
}
