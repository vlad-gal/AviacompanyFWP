package by.halatsevich.company.controller.command;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.command.impl.*;
import by.halatsevich.company.controller.command.impl.page.*;

public enum CommandType {
    ADD_USER_INTO_CREW(new AddUserIntoCrewCommand()),
    ADD_USER_INTO_CREW_PAGE(new AddUserIntoCrewPageCommand()),
    ADMIN_UPDATE_USER(new AdminUpdateUserCommand()),
    ADMIN_UPDATE_USER_PAGE(new AdminUpdateUserPageCommand()),
    AIRCRAFTS_PAGE(new AircraftsPageCommand()),
    AIRPORTS_PAGE(new AirportsPageCommand()),
    ALL_AIRCRAFTS(new AllAircraftsCommand()),
    ALL_AIRPORTS(new AllAirportsCommand()),
    ALL_CREWS(new AllCrewsCommand()),
    ALL_FLIGHTS(new AllFlightsCommand()),
    ALL_USERS(new AllUsersCommand()),
    ALL_USER_CREWS(new AllUserCrewsCommand()),
    ALL_USER_FLIGHTS(new AllUserFlightsCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    AUTHORIZATION_PAGE(request -> PagePath.AUTHORIZATION),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_PASSWORD_PAGE(request -> PagePath.CHANGE_PASSWORD),
    CONFIRM_ACCOUNT(new ConfirmAccountCommand()),
    CREATE_AIRCRAFT(new CreateAircraftCommand()),
    CREATE_AIRCRAFT_PAGE(new CreateAircraftPageCommand()),
    CREATE_AIRPORT(new CreateAirportCommand()),
    CREATE_AIRPORT_PAGE(request -> PagePath.CREATE_AIRPORT),
    CREATE_CREW(new CreateCrewCommand()),
    CREATE_CREW_PAGE(request -> PagePath.CREATE_CREW),
    CREATE_FLIGHT(new CreateFlightCommand()),
    CREATE_FLIGHT_PAGE(new CreateFlightPageCommand()),
    CREATE_USER(new CreateUserCommand()),
    CREATE_USER_PAGE(request -> PagePath.CREATE_USER),
    FLIGHTS_PAGE(new FlightsPageCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    FORGOT_PASSWORD_PAGE(request -> PagePath.FORGOT_PASSWORD),
    SETTINGS_PAGE(request -> PagePath.SETTING_PAGE),
    SHOW_CREW(new ShowCrewPageCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    RESET_PASSWORD_PAGE(new ResetPasswordPageCommand()),
    LOCALIZATION(new LocalizationCommand()),
    LOG_OUT(new LogOutCommand()),
    PAGINATION(new PaginationCommand()),
    UPDATE_AIRCRAFT(new UpdateAircraftCommand()),
    UPDATE_AIRCRAFT_PAGE(new UpdateAircraftPageCommand()),
    UPDATE_AIRPORT(new UpdateAirportCommand()),
    UPDATE_AIRPORT_PAGE(new UpdateAirportPageCommand()),
    UPDATE_CREW(new UpdateCrewCommand()),
    UPDATE_CREW_PAGE(new UpdateCrewPageCommand()),
    UPDATE_FLIGHT(new UpdateFlightCommand()),
    UPDATE_FLIGHT_PAGE(new UpdateFlightPageCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    USER_ACCOUNT_PAGE(request -> PagePath.USER_ACCOUNT),
    WELCOME_PAGE(new WelcomePageCommand()),
    WRONG_ACTION(new WrongActionCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
