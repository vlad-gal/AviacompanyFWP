package by.halatsevich.company.controller;

import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.controller.command.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

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