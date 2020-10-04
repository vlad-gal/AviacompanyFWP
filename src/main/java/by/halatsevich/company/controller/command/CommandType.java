package by.halatsevich.company.controller.command;

import by.halatsevich.company.controller.command.impl.*;

public enum CommandType {
    WRONG_ACTION(new WrongActionCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGOUT(new LogOutCommand()),
    SELECTAIRCRAFT(new ShowAllAircraftsCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
