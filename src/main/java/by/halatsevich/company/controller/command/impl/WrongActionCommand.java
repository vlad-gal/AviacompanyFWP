package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class WrongActionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ERROR_404;
    }
}
