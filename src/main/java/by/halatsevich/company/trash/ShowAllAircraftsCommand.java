package by.halatsevich.company.trash;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllAircraftsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        AircraftService aircraftService = factory.getAircraftService();
        List<Aircraft> aircrafts = null;
        try {
            aircrafts = aircraftService.findAllAircrafts();
        } catch (ServiceException e) {
//            response.sendRedirect(PagePath.ERROR_PAGE);
        }
        request.setAttribute(ParameterName.AIRCRAFT_LIST, aircrafts);
        request.getRequestDispatcher(PagePath.AIRCRAFTS_PAGE).forward(request,response);
//        response.sendRedirect(PagePath.WELCOME_PAGE);
    }
}
