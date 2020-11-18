package by.halatsevich.company.controller;

import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.controller.command.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class represents command provider which define command from request.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);
    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    /**
     * Gets instance of command provider.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Define command.
     *
     * @param commandName the command name
     * @return the command
     */
    public Command defineCommand(String commandName) {
        if (commandName == null || commandName.isEmpty()) {
            logger.log(Level.INFO, "Name of command is null or empty");
            return CommandType.WRONG_ACTION.getCommand();
        }
        Command command;
        try {
            command = CommandType.valueOf(commandName.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "Error while define command", e);
            return CommandType.WRONG_ACTION.getCommand();
        }
        return command;
    }
}