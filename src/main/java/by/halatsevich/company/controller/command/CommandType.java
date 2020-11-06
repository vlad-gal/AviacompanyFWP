package by.halatsevich.company.controller.command;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.impl.*;
import by.halatsevich.company.controller.command.impl.page.*;

public enum CommandType {
    WRONG_ACTION(new WrongActionCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    ALL_USERS(new AllUsersCommand()),
    AUTHORIZATION_PAGE(request -> PagePath.AUTHORIZATION),
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
    FORGOT_PASSWORD_PAGE(request -> PagePath.FORGOT_PASSWORD),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    RESET_PASSWORD_PAGE(new ResetPasswordPageCommand()),
    PAGINATION(new PaginationCommand()),
    SETTINGS_PAGE(request -> PagePath.SETTING_PAGE),
    USER_ACCOUNT_PAGE(request -> PagePath.USER_ACCOUNT),
    UPDATE_USER(new UpdateUserCommand()),
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    CHANGE_PASSWORD_PAGE(request ->  PagePath.CHANGE_PASSWORD),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CREATE_USER_PAGE(request -> PagePath.CREATE_USER),
    CREATE_USER(new CreateUserCommand()),
    CREATE_FLIGHT_PAGE(new CreateFlightPageCommand()),
    ADMIN_UPDATE_USER_PAGE(new AdminUpdateUserPageCommand()),
    ADMIN_UPDATE_USER(new AdminUpdateUserCommand()),
    ALL_FLIGHTS(new AllFlightCommand()),
    CREATE_FLIGHT(new CreateFlightCommand()),
    UPDATE_FLIGHT_PAGE(new UpdateFlightPageCommand()),
    FLIGHT_DETAIL_PAGE(new FlightDetailPageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
