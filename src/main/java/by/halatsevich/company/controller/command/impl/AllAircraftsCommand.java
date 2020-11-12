package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.AircraftService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllAircraftsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllAircraftsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String status = request.getParameter(ParameterName.STATUS);
        HttpSession session = request.getSession();
        String page;
        if (BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            AircraftService aircraftService = factory.getAircraftService();
            try {
                List<Aircraft> aircrafts = aircraftService.findAircraftsByStatus(status);
                session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
                session.setAttribute(ParameterName.AIRCRAFT_LIST, aircrafts);
                request.setAttribute(ParameterName.SHOW_AIRCRAFTS_FLAG, true);
                page = PagePath.USER_ACCOUNT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding all airports", e);
                request.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR_500;
            }
        } else {
            logger.log(Level.ERROR, "Invalid status");
            page = PagePath.ERROR_404;
        }
        return page;
    }
}