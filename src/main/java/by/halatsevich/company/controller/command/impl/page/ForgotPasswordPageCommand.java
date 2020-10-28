package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ForgotPasswordPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return PagePath.FORGOT_PASSWORD;
    }
}
