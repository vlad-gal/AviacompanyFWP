package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents preparation for forwarding to the aircrafts page.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AircraftsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AircraftsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        AircraftService aircraftService = factory.getAircraftService();
        String page;
        List<Aircraft> aircraftList;
        try {
            aircraftList = aircraftService.findAllAircrafts();
            session.setAttribute(ParameterName.AIRCRAFT_LIST, aircraftList);
            session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
            page = PagePath.AIRCRAFTS;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all aircrafts", e);
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
