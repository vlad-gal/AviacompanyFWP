package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ResetPasswordPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return null;
    }
}
