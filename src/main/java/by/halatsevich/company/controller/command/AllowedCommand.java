package by.halatsevich.company.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.halatsevich.company.controller.command.CommandType.*;

/**
 * The enum which define all allowed commands for user with various roles.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public enum AllowedCommand {

    /**
     * Admin's allowed commands.
     */
    ADMIN(EnumSet.of(
            ADMIN_UPDATE_USER,
            ADMIN_UPDATE_USER_PAGE,
            AIRCRAFTS_PAGE,
            AIRPORTS_PAGE,
            ALL_AIRCRAFTS,
            ALL_AIRPORTS,
            ALL_CREWS,
            ALL_FLIGHTS,
            ALL_USERS,
            AUTHORIZATION,
            AUTHORIZATION_PAGE,
            CHANGE_PASSWORD,
            CHANGE_PASSWORD_PAGE,
            CONFIRM_ACCOUNT,
            CREATE_AIRCRAFT,
            CREATE_AIRCRAFT_PAGE,
            CREATE_AIRPORT,
            CREATE_AIRPORT_PAGE,
            CREATE_USER,
            CREATE_USER_PAGE,
            FLIGHTS_PAGE,
            FORGOT_PASSWORD,
            FORGOT_PASSWORD_PAGE,
            SETTINGS_PAGE,
            SHOW_CREW,
            REGISTRATION,
            REGISTRATION_PAGE,
            RESET_PASSWORD,
            RESET_PASSWORD_PAGE,
            LOCALIZATION,
            LOG_OUT,
            PAGINATION,
            UPDATE_AIRCRAFT,
            UPDATE_AIRCRAFT_PAGE,
            UPDATE_AIRPORT,
            UPDATE_AIRPORT_PAGE,
            UPDATE_CREW,
            UPDATE_CREW_PAGE,
            UPDATE_FLIGHT,
            UPDATE_FLIGHT_PAGE,
            UPDATE_USER,
            USER_ACCOUNT_PAGE,
            WELCOME_PAGE,
            WRONG_ACTION)),

    /**
     * Dispatcher's allowed commands.
     */
    DISPATCHER(EnumSet.of(ADD_USER_INTO_CREW,
            ADD_USER_INTO_CREW_PAGE,
            AIRCRAFTS_PAGE,
            AIRPORTS_PAGE,
            ALL_CREWS,
            ALL_USERS,
            AUTHORIZATION,
            AUTHORIZATION_PAGE,
            CHANGE_PASSWORD,
            CHANGE_PASSWORD_PAGE,
            CONFIRM_ACCOUNT,
            CREATE_CREW,
            CREATE_CREW_PAGE,
            FLIGHTS_PAGE,
            FORGOT_PASSWORD,
            FORGOT_PASSWORD_PAGE,
            SETTINGS_PAGE,
            SHOW_CREW,
            REGISTRATION,
            REGISTRATION_PAGE,
            RESET_PASSWORD,
            RESET_PASSWORD_PAGE,
            LOCALIZATION,
            LOG_OUT,
            PAGINATION,
            UPDATE_CREW,
            UPDATE_CREW_PAGE,
            UPDATE_USER,
            USER_ACCOUNT_PAGE,
            WELCOME_PAGE,
            WRONG_ACTION)),

    /**
     * Operator's allowed commands.
     */
    OPERATOR(EnumSet.of(AIRCRAFTS_PAGE,
            AIRPORTS_PAGE,
            ALL_AIRCRAFTS,
            ALL_AIRPORTS,
            ALL_CREWS,
            ALL_FLIGHTS,
            AUTHORIZATION,
            AUTHORIZATION_PAGE,
            CHANGE_PASSWORD,
            CHANGE_PASSWORD_PAGE,
            CONFIRM_ACCOUNT,
            CREATE_FLIGHT,
            CREATE_FLIGHT_PAGE,
            FLIGHTS_PAGE,
            FORGOT_PASSWORD,
            FORGOT_PASSWORD_PAGE,
            SETTINGS_PAGE,
            SHOW_CREW,
            REGISTRATION,
            REGISTRATION_PAGE,
            RESET_PASSWORD,
            RESET_PASSWORD_PAGE,
            LOCALIZATION,
            LOG_OUT,
            PAGINATION,
            UPDATE_FLIGHT,
            UPDATE_FLIGHT_PAGE,
            UPDATE_USER,
            USER_ACCOUNT_PAGE,
            WELCOME_PAGE,
            WRONG_ACTION)),

    /**
     * Staff's allowed commands.
     */
    STAFF(EnumSet.of(AIRCRAFTS_PAGE,
            AIRPORTS_PAGE,
            ALL_USER_CREWS,
            ALL_USER_FLIGHTS,
            AUTHORIZATION,
            AUTHORIZATION_PAGE,
            CHANGE_PASSWORD,
            CHANGE_PASSWORD_PAGE,
            CONFIRM_ACCOUNT,
            FLIGHTS_PAGE,
            FORGOT_PASSWORD,
            FORGOT_PASSWORD_PAGE,
            SETTINGS_PAGE,
            SHOW_CREW,
            REGISTRATION,
            REGISTRATION_PAGE,
            RESET_PASSWORD,
            RESET_PASSWORD_PAGE,
            LOCALIZATION,
            LOG_OUT,
            PAGINATION,
            UPDATE_USER,
            USER_ACCOUNT_PAGE,
            WELCOME_PAGE,
            WRONG_ACTION)),

    /**
     * Guest's allowed commands.
     */
    GUEST(EnumSet.of(
            AIRCRAFTS_PAGE,
            AIRPORTS_PAGE,
            AUTHORIZATION,
            AUTHORIZATION_PAGE,
            CHANGE_PASSWORD,
            CHANGE_PASSWORD_PAGE,
            CONFIRM_ACCOUNT,
            FLIGHTS_PAGE,
            FORGOT_PASSWORD,
            FORGOT_PASSWORD_PAGE,
            REGISTRATION,
            REGISTRATION_PAGE,
            RESET_PASSWORD,
            RESET_PASSWORD_PAGE,
            LOCALIZATION,
            LOG_OUT,
            PAGINATION,
            WELCOME_PAGE,
            WRONG_ACTION));

    private Set<CommandType> commands;

    AllowedCommand(Set<CommandType> commands) {
        this.commands = commands;
    }

    /**
     * Gets all commands.
     *
     * @return set with allowed commands
     */
    public Set<CommandType> getCommands() {
        return commands;
    }
}