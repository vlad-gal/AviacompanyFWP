package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lang = request.getParameter(ParameterName.LANG);
        session.setAttribute(ParameterName.LANG, lang);
        return (String) session.getAttribute(ParameterName.CURRENT_PAGE);
    }
}
