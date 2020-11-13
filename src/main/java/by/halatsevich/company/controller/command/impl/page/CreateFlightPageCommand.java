package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Airport;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.AirportService;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents preparation for creating flight.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CreateFlightPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateFlightPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServiceFactory factory = ServiceFactory.getInstance();
        CrewService crewService = factory.getCrewService();
        AirportService airportService = factory.getAirportService();
        AircraftService aircraftService = factory.getAircraftService();
        List<Crew> crews;
        List<Airport> airports;
        List<Aircraft> aircrafts;
        String page;
        try {
            crews = crewService.findCrewsByStatus(Status.ACTIVE.getStatusName());
            airports = airportService.findAllAirports();
            aircrafts = aircraftService.findAircraftsByStatus(Status.ACTIVE.getStatusName());
            session.setAttribute(ParameterName.CREWS, crews);
            session.setAttribute(ParameterName.AIRPORTS, airports);
            session.setAttribute(ParameterName.AIRCRAFTS, aircrafts);
            page = PagePath.CREATE_FLIGHT;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all parts of crews", e);
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR_500;
        }
        return page;
    }
}
