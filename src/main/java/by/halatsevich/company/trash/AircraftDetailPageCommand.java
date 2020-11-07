//package by.halatsevich.company.controller.command.impl.page;
//
//import by.halatsevich.company.controller.PagePath;
//import by.halatsevich.company.controller.ParameterName;
//import by.halatsevich.company.controller.command.Command;
//import by.halatsevich.company.model.entity.Aircraft;
//import by.halatsevich.company.model.exception.ServiceException;
//import by.halatsevich.company.model.service.AircraftService;
//import by.halatsevich.company.model.service.ServiceFactory;
//import by.halatsevich.company.validator.BaseValidator;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//public class AircraftDetailPageCommand implements Command {
//    private static final Logger logger = LogManager.getLogger(AircraftDetailPageCommand.class);
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        String aircraftId = request.getParameter(ParameterName.AIRCRAFT_ID);
//        String page;
//        if (BaseValidator.isValidId(aircraftId)) {
//            ServiceFactory factory = ServiceFactory.getInstance();
//            AircraftService aircraftService = factory.getAircraftService();
//            try {
//                Optional<Aircraft> aircraft = aircraftService.findAircraftById(aircraftId);
//                if (aircraft.isPresent()) {
//                    request.setAttribute(ParameterName.AIRCRAFT, aircraft.get());
//                    page = PagePath.DETAIL_PAGE;
//                } else {
//                    request.setAttribute(ParameterName.AIRCRAFT_NOT_FOUND_FLAG, true);
//                    page = PagePath.DETAIL_PAGE;
//                }
//            } catch (ServiceException e) {
//                logger.log(Level.ERROR, "Error while finding aircraft by id", e);
//                request.setAttribute(ParameterName.ERROR_MESSAGE, e);
//                page = PagePath.ERROR_500;
//            }
//        } else {
//            logger.log(Level.WARN, "Incorrect aircraft id");
//            request.setAttribute(ParameterName.INCORRECT_ID_FLAG, true);
//            page = PagePath.DETAIL_PAGE;
//        }
//        return page;
//    }
//}
