package by.halatsevich.company.model.dao;

/**
 * The class represents sql queries.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class SqlQuery {
    /**
     * Sql queries for users table
     */
    public static final String SELECT_ALL_USERS =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId";
    public static final String SELECT_ALL_USERS_BY_STATUS =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE users.statusId = ?";
    public static final String SELECT_ALL_USERS_BY_ROLE_AND_STATUS =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE (users.statusId = ? AND users.roleId = ?)";
    public static final String SELECT_USER_BY_LOGIN =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE users.login = ?";
    public static final String SELECT_USER_BY_ID =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE users.userId = ?";
    public static final String SELECT_USER_BY_EMAIL =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE users.email = ?";
    public static final String SELECT_USER_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE login = ?";
    public static final String INSERT_USER =
            "INSERT INTO users (email, login, password, roleId, statusId) " +
                    "VALUES (?,?,?,?,?)";
    public static final String INSERT_PERSONAL_DATA =
            "INSERT INTO personalData (userId, firstName, lastName, telephoneNumber)" +
                    "VALUES (?,?,?,?)";
    public static final String REMOVE_USER_BY_ID =
            "UPDATE users SET statusId = '?' WHERE userId = ?";
    public static final String UPDATE_USER =
            "UPDATE users SET email = ?, login = ?, roleId = ?, statusId = ? WHERE login = ?";
    public static final String UPDATE_PASSWORD =
            "UPDATE users SET password = ? WHERE email = ?";
    public static final String UPDATE_PERSONAL_DATA =
            "UPDATE personalData SET firstName = ?, lastName = ?, telephoneNumber = ? WHERE userId = ?";

    /**
     * Sql queries for flights table
     */
    public static final String SELECT_ALL_FLIGHTS =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId";
    public static final String SELECT_ALL_FLIGHTS_BY_STATUS =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.statusId = ?";
    public static final String SELECT_ALL_USERS_FLIGHTS_BY_STATUS =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN crews ON crews.crewId = flights.crewId " +
                    "JOIN crews_has_users ON crews.crewId = crews_has_users.crewId " +
                    "JOIN users ON users.userId = crews_has_users.userId " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE (flights.statusId = ? AND users.userId = ?)";
    public static final String SELECT_FLIGHT_BY_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.flightId = ?";
    public static final String INSERT_FLIGHT =
            "INSERT INTO flights (departureAirportId, destinationAirportId, departTime, arriveTime, aircraftId, " +
                    "operatorId, crewId, statusId) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_FLIGHT =
            "UPDATE flights SET departureAirportId = ?, destinationAirportId = ?, departTime = ?, arriveTime = ?, " +
                    "aircraftId = ?, operatorId = ?, crewId = ?, statusId = ? WHERE flightId = ?";
    public static final String REMOVE_FLIGHT_BY_ID =
            "UPDATE flights SET statusId = ? WHERE flightId = ?";

    /**
     * Sql queries for crews and crews_has_users table
     */
    public static final String SELECT_ALL_CREWS =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName, crews.crewName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId";
    public static final String SELECT_ALL_CREWS_BY_STATUS =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName, crews.crewName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE crews.statusId = ?";
    public static final String SELECT_CREW_BY_ID =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName, crews.crewName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE crews.crewId = ?";
    public static final String SELECT_CREW_BY_CREW_NAME =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName, crews.crewName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE crews.crewName = ?";
    public static final String SELECT_USERS_ROLE_BY_CREW_ID =
            "SELECT roles.roleName " +
                    "FROM crews_has_users " +
                    "JOIN users ON users.userId = crews_has_users.userId " +
                    "JOIN roles ON users.roleId = roles.roleId " +
                    "WHERE crewId = ?";
    public static final String SELECT_USERS_ID_BY_CREW_ID =
            "SELECT userId FROM crews_has_users WHERE crewId = ?";
    public static final String SELECT_USERS_CREWS_BY_STATUS =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName, crews.crewName " +
                    "FROM crews " +
                    "JOIN crews_has_users ON crews.crewId = crews_has_users.crewId " +
                    "JOIN users ON users.userId = crews_has_users.userId " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE (crews.statusId = ? AND users.userId = ?)";
    public static final String INSERT_CREW =
            "INSERT INTO crews (dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, statusId, crewName) " +
                    "VALUES (?,?,?,?,?,?,?)";
    public static final String INSERT_USER_INTO_CREW =
            "INSERT INTO crews_has_users (crewId, userId) VALUES (?,?)";
    public static final String UPDATE_CREW =
            "UPDATE crews SET numberOfPilots = ?, numberOfNavigators = ?, numberOfRadioman = ?, " +
                    "numberOfStewardesses = ?, statusId = ? WHERE crewId = ?";
    public static final String REMOVE_CREW_BY_ID =
            "UPDATE crews SET statusId = ? WHERE crewId = ?";

    /**
     * Sql queries for airports table
     */
    public static final String SELECT_ALL_AIRPORTS =
            "SELECT airportId, airportName, country, city FROM airports";
    public static final String SELECT_AIRPORT_BY_ID =
            "SELECT airportId, airportName, country, city FROM airports WHERE airportId = ?";
    public static final String INSERT_AIRPORT =
            "INSERT INTO airports (airportName, country, city) VALUES (?,?,?)";
    public static final String UPDATE_AIRPORT =
            "UPDATE airports SET airportName = ?, country = ?, city = ? WHERE airportId = ?";

    /**
     * Sql queries for aircrafts table
     */
    public static final String SELECT_ALL_AIRCRAFT =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType, statuses.statusName " +
                    "FROM aircrafts " +
                    "JOIN statuses ON statuses.statusId = aircrafts.statusId " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId";
    public static final String SELECT_ALL_AIRCRAFT_BY_STATUS =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType, statuses.statusName " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "JOIN statuses ON statuses.statusId = aircrafts.statusId " +
                    "WHERE aircrafts.statusId = ?";
    public static final String SELECT_AIRCRAFT_BY_ID =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType, statuses.statusName " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "JOIN statuses ON statuses.statusId = aircrafts.statusId " +
                    "WHERE aircrafts.aircraftId = ?";
    public static final String INSERT_AIRCRAFT =
            "INSERT INTO aircrafts (tailNumber, aircraftName, aircraftTypeId, statusId) VALUES (?,?,?,?)";
    public static final String UPDATE_AIRCRAFT =
            "UPDATE aircrafts SET tailNumber = ?, aircraftName = ?, aircraftTypeId = ?, statusId = ? WHERE aircraftId = ?";
    public static final String REMOVE_AIRCRAFT_BY_ID =
            "UPDATE aircrafts SET statusId = '?' WHERE aircraftId = ?";

    private SqlQuery() {
    }
}
