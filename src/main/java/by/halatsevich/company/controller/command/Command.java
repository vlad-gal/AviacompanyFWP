package by.halatsevich.company.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command which define functionality of commands.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface Command {
    /**
     * Execute command.
     *
     * @param request the request
     * @return the string contains page path
     */
    String execute(HttpServletRequest request);
}
