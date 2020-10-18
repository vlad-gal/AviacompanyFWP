package by.halatsevich.company.controller.command;

import by.halatsevich.company.controller.command.impl.*;
import by.halatsevich.company.controller.command.impl.page.*;

public enum CommandType {
    WRONG_ACTION(new WrongActionCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    AUTHORIZATION_PAGE(new AuthorizationPageCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    LOG_OUT(new LogOutCommand()),
    WELCOME_PAGE(new WelcomePageCommand()),
    AIRCRAFTS_PAGE(new AircraftsPageCommand()),
    AIRPORTS_PAGE(new AirportsPageCommand()),
    FLIGHTS_PAGE(new FlightsPageCommand()),
    LOCALIZATION(new LocalizationCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
