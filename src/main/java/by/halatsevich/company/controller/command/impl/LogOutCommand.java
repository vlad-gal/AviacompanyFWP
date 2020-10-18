package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(ParameterName.USER_ID, null);
        request.getSession().setAttribute(ParameterName.USER_FIRST_NAME,null);
        request.getSession().setAttribute(ParameterName.USER_LAST_NAME,null);
        request.getSession().setAttribute(ParameterName.USER_EMAIL,null);
        request.getSession().setAttribute(ParameterName.USER_TELEPHONE_NUMBER,null);
        request.getSession().setAttribute(ParameterName.USER_LOGIN,null);
        request.getSession().setAttribute(ParameterName.USER_ROLE,null);
        request.getSession().setAttribute(ParameterName.USER_STATUS,null);
        response.sendRedirect(PagePath.INDEX_PAGE);
    }
}
