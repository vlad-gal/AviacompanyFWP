package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Crew;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.validator.BaseValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents command to show all crews on user account.
 * This command find all crews based on their status.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AllCrewsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AllCrewsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String status = request.getParameter(ParameterName.STATUS);
        String page;
        if (BaseValidator.isValidStatus(status)) {
            ServiceFactory factory = ServiceFactory.getInstance();
            CrewService service = factory.getCrewService();
            try {
                List<Crew> crews = service.findCrewsByStatus(status);
                session.setAttribute(ParameterName.CURRENT_PAGE_NUMBER, 1);
                session.setAttribute(ParameterName.ALL_CREW_LIST, crews);
                request.setAttribute(ParameterName.SHOW_CREWS_FLAG, true);
                page = PagePath.USER_ACCOUNT;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error while finding all crews by status", e);
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
