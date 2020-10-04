package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.service.FlightService;
import by.halatsevich.company.service.ServiceFactory;
import by.halatsevich.company.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllAircraftsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        FlightService service = factory.getFlightService();
        List<Aircraft> aircrafts = null;
        try {
            aircrafts = service.selectAllAircraft();
        } catch (ServiceException e) {

            response.sendRedirect(PagePath.ERROR_PAGE);
        }
        request.getSession().setAttribute(ParameterName.AIRCRAFT_LIST, aircrafts);
        response.sendRedirect(PagePath.WELCOME_PAGE);
    }
}
