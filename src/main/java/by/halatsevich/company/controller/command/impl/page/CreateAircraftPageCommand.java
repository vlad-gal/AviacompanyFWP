package by.halatsevich.company.controller.command.impl.page;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.entity.Aircraft;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents preparation for forwarding to the create aircraft page.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CreateAircraftPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(ParameterName.AIRCRAFT_TYPES, Aircraft.AircraftType.values());
        return PagePath.CREATE_AIRCRAFT;
    }
}
