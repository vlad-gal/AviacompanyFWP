package by.halatsevich.company.controller.filter;

import by.halatsevich.company.controller.CommandProvider;
import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.AllowedCommand;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.controller.command.CommandType;
import by.halatsevich.company.controller.command.impl.WrongActionCommand;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class UserSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String commandName = request.getParameter(ParameterName.COMMAND);
        Set<CommandType> commandTypeSet = null;

        if (user != null) {
            if (user.getStatus() != Status.INACTIVE) {
                Command command = CommandProvider.getInstance().defineCommand(commandName);
                if (command.getClass() != WrongActionCommand.class) {
                    switch (user.getRole()) {
                        case ADMIN:
                            commandTypeSet = AllowedCommand.ADMIN.getCommands();
                            break;
                        case DISPATCHER:
                            commandTypeSet = AllowedCommand.DISPATCHER.getCommands();
                            break;
                        case OPERATOR:
                            commandTypeSet = AllowedCommand.OPERATOR.getCommands();
                            break;
                        case PILOT:
                        case NAVIGATOR:
                        case RADIOMAN:
                        case STEWARDESS:
                            commandTypeSet = AllowedCommand.STAFF.getCommands();
                            break;
                    }
                }
                if (commandTypeSet != null && !commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                    response.sendError(403, PagePath.ERROR_404);
                    return;
                }
            } else {
                Command command = CommandProvider.getInstance().defineCommand(commandName);
                if (command.getClass() != WrongActionCommand.class) {
                    commandTypeSet = AllowedCommand.GUEST.getCommands();
                }
                if (commandTypeSet != null && !commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                    response.sendError(403, PagePath.ERROR_404);
                    return;
                }
            }
        } else {
            Command command = CommandProvider.getInstance().defineCommand(commandName);
            if (command.getClass() != WrongActionCommand.class) {
                commandTypeSet = AllowedCommand.GUEST.getCommands();
            }
            if (commandTypeSet != null && !commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                response.sendError(403, PagePath.ERROR_404);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
