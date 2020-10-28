package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WrongActionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return PagePath.NOT_FOUND_COMMAND;
    }
}
