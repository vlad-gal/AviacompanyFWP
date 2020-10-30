package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.AUTHORIZATION;
    }
}
