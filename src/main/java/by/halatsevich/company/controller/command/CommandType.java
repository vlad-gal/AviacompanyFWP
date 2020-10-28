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
    LOCALIZATION(new LocalizationCommand()),
    AIRPORT_DETAIL_PAGE(new AirportDetailPageCommand()),
    AIRCRAFT_DETAIL_PAGE(new AircraftDetailPageCommand()),
    FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    USER_ACCOUNT_PAGE(new UserAccountPageCommand()),
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    FLIGHT_DETAIL_PAGE(new FlightDetailPageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
