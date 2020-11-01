package by.halatsevich.company.controller.command;

import by.halatsevich.company.controller.command.impl.*;
import by.halatsevich.company.controller.command.impl.page.*;

public enum CommandType {
    WRONG_ACTION(new WrongActionCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    ALL_USERS(new AllUsersCommand()),
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
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    RESET_PASSWORD_PAGE(new ResetPasswordPageCommand()),
    PAGINATION(new PaginationCommand()),
    SETTINGS_PAGE(new SettingsPageCommand()),
    USER_ACCOUNT_PAGE(new UserAccountPageCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    CHANGE_PASSWORD_PAGE(new ChangePasswordPageCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    FLIGHT_DETAIL_PAGE(new FlightDetailPageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
