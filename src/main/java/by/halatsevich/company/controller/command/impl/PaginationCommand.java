package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents pagination command.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class PaginationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String direction = request.getParameter(ParameterName.DIRECTION);
        int currentPage = (int) request.getSession().getAttribute(ParameterName.CURRENT_PAGE_NUMBER);
        if (direction.equals(ParameterName.PREVIOUS)) {
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_NUMBER, --currentPage);
        }
        if (direction.equals(ParameterName.NEXT)) {
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_NUMBER, ++currentPage);
        }
        return (String) request.getSession().getAttribute(ParameterName.CURRENT_PAGE);
    }
}
