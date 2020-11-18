package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents command to log out from user account.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagePath.INDEX;
    }
}
