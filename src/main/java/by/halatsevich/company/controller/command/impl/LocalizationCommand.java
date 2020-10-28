package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.RequestHandler;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LocalizationCommand implements Command {
    private static final String DEFAULT_LANG = "ru";

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter(ParameterName.LANG);
        if (lang == null || lang.isEmpty()) {
            lang = DEFAULT_LANG;
        }
        session.setAttribute(ParameterName.LANG, lang);
        RequestHandler handler = (RequestHandler) session.getAttribute(ParameterName.REQUEST_ATTRIBUTES);
        for (Map.Entry<String, Object> parameters : handler.getAttributes().entrySet()) {
            request.setAttribute(parameters.getKey(), parameters.getValue());
        }
        return (String) session.getAttribute(ParameterName.CURRENT_PAGE);
    }
}
