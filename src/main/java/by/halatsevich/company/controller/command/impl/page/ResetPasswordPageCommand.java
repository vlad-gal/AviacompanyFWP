package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ResetPasswordPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(ParameterName.EMAIL, request.getParameter(ParameterName.EMAIL));
        return PagePath.RESET_PASSWORD;
    }
}
