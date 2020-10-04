package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongActionCommand implements Command {
    private static final String COMMAND_DO_NOT_DEFINE = "Command do not define";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(ParameterName.NOT_FOUND_COMMAND_MESSAGE, COMMAND_DO_NOT_DEFINE);
        response.sendRedirect(PagePath.NOT_FOUND_COMMAND_PAGE);
    }
}
