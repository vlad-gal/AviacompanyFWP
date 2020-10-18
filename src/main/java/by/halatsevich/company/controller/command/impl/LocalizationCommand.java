package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LocalizationCommand.class);
    private static final String DEFAULT_LANG = "ru-RU";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter(ParameterName.SESSION_LANG_ATTRIBUTE);
        if (lang.isEmpty()) {
            lang = DEFAULT_LANG;
        }
        request.getSession().setAttribute(ParameterName.SESSION_LANG_ATTRIBUTE, lang);
        String currentPage = (String) request.getSession().getAttribute(ParameterName.CURRENT_PAGE);
        logger.log(Level.INFO, currentPage);
        request.getRequestDispatcher(currentPage).forward(request, response);
    }
}
