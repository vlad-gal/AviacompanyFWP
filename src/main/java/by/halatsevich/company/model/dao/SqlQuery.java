package by.halatsevich.company.model.dao;

public class SqlQuery {
    //users
    public static final String SELECT_ALL_USERS =
            "SELECT users.userId, users.email, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber " +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId " +
                    "JOIN personalData ON personalData.userId = users.userId";
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
    public static final String SELECT_USER_LOGIN =
            "SELECT login FROM users WHERE login = ?";
    public static final String SELECT_USER_EMAIL =
            "SELECT email FROM users WHERE email = ?";

    public static final String SELECT_USER_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE login = ?"; // TODO: 20.10.2020 test
    public static final String INSERT_USER =
            "INSERT INTO users (email, login, password, roleId, statusId) " +
                    "VALUES (?,?,?,?,?)";
    public static final String INSERT_PERSONAL_DATA =
            "INSERT INTO personalData (userId, firstName, lastName, telephoneNumber)" +
                    "VALUES (?,?,?,?)";
    public static final String REMOVE_USER_BY_ID =
            "UPDATE users SET statusId = '2' WHERE userId = ?";
    public static final String UPDATE_USER =
            "UPDATE users SET email = ?, login = ?, roleId = ?, statusId = ? WHERE login = ?";
    public static final String UPDATE_PASSWORD = // TODO: 20.10.2020 check
            "UPDATE users SET password = ? WHERE email = ?";
    public static final String UPDATE_PERSONAL_DATA =
            "UPDATE personalData SET firstName = ?, lastName = ?, telephoneNumber = ? WHERE userId = ?";
    //flights

    public static final String SELECT_ALL_FLIGHTS =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId";
    public static final String SELECT_FLIGHT_BY_DEPARTURE_AIRPORT_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.flightId " +
                    "WHERE flights.departureAirportId = ?";
    public static final String SELECT_FLIGHT_BY_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.flightId = ?"; // TODO: 20.10.2020 check
    public static final String SELECT_FLIGHT_BY_DESTINATION_AIRPORT_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.destinationAirportId = ?";
    public static final String SELECT_FLIGHT_BY_DEPART_PERIOD_OF_TIME =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE (flights.departTime >= ? AND flights.departTime <= ?)";
    public static final String SELECT_FLIGHT_BY_ARRIVE_PERIOD_OF_TIME =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE (flights.arriveTime >= ? AND flights.arriveTime <= ?)";
    public static final String SELECT_FLIGHT_BY_CREW_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.crewId = ?";
    public static final String SELECT_FLIGHT_BY_OPERATOR_ID =
            "SELECT flights.flightId, flights.departureAirportId, flights.destinationAirportId, flights.departTime, " +
                    "flights.arriveTime, flights.aircraftId, flights.operatorId, flights.crewId, statuses.statusName " +
                    "FROM flights " +
                    "JOIN statuses ON statuses.statusId = flights.statusId " +
                    "WHERE flights.operatorId = ?";
    public static final String INSERT_FLIGHT =
            "INSERT INTO flights (departureAirportId, destinationAirportId, departTime, arriveTime, aircraftId, " +
                    "operatorId, crewId, statusId) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_FLIGHT =
            "UPDATE flights SET departureAirportId = ?, destinationAirportId = ?, departTime = ?, arriveTime = ?, " +
                    "aircraftId = ?, operatorId = ?, crewId = ?, statusId = ? WHERE flightId = ?";
    public static final String REMOVE_FLIGHT_BY_ID =
            "UPDATE flights SET statusId = ? WHERE flightId = ?";
    //crew

    public static final String SELECT_ALL_CREWS =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId";
    public static final String SELECT_CREW_BY_ID =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE crews.crewId = ?";
    public static final String SELECT_CREW_BY_DISPATCHER_ID =
            "SELECT crews.crewId, crews.dispatcherId, crews.numberOfPilots, crews.numberOfNavigators, crews.numberOfRadioman, " +
                    "crews.numberOfStewardesses, statuses.statusName " +
                    "FROM crews " +
                    "JOIN statuses ON statuses.statusId = crews.statusId " +
                    "WHERE crews.dispatcherId = ?";
    public static final String SELECT_USERS_ID_BY_CREW_ID =
            "SELECT userId FROM crews_has_users WHERE crewId = ?";
    public static final String INSERT_CREW =
            "INSERT INTO crews (dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, statusId) " +
                    "VALUES (?,?,?,?,?,?)";
    public static final String INSERT_USER_INTO_CREW =
            "INSERT INTO crews_has_users (crewId, userId) VALUES (?,?)";
    public static final String UPDATE_USER_INTO_CREW =
            "UPDATE crews_has_users SET userId = ? WHERE (userId = ? AND crewId = ?)";
    public static final String UPDATE_DISPATCHER_INTO_CREW =
            "UPDATE crews SET dispatcherId = ? WHERE (dispatcherId = ? AND crewId = ?)";
    public static final String UPDATE_CREW =
            "UPDATE crews SET dispatcherId = ?, numberOfPilots = ?, numberOfNavigators = ?, numberOfRadioman = ?, " +
                    "numberOfStewardesses = ?, statusId = ? WHERE crewId = ?";
    public static final String REMOVE_CREW_BY_ID =
            "UPDATE crews SET statusId = ? WHERE crewId = ?";
    public static final String REMOVE_USER_FROM_CREW_BY_ID =
            "DELETE FROM crews_has_users WHERE (userId = ? AND crewId = ?)";
    //airport

    public static final String SELECT_ALL_AIRPORTS =
            "SELECT airportId, airportName, country, city FROM airports";
    public static final String SELECT_AIRPORT_BY_ID =
            "SELECT airportId, airportName, country, city FROM airports WHERE airportId = ?";
    public static final String SELECT_AIRPORT_BY_NAME =
            "SELECT airportId, airportName, country, city FROM airports WHERE airportName = ?";
    public static final String SELECT_AIRPORTS_BY_CITY =
            "SELECT airportId, airportName, country, city FROM airports WHERE city = ?";
    public static final String SELECT_AIRPORTS_BY_COUNTRY =
            "SELECT airportId, airportName, country, city FROM airports WHERE country = ?";
    public static final String INSERT_AIRPORT =
            "INSERT INTO airports (airportName, country, city) VALUES (?,?,?)";
    public static final String UPDATE_AIRPORT =
            "UPDATE airports SET airportName = ?, country = ?, city = ? WHERE airportId = ?";
    public static final String REMOVE_AIRPORT_BY_ID =
            "DELETE FROM airports WHERE airportId = ?"; // TODO: 20.10.2020 check
    //aircraft

    public static final String SELECT_ALL_AIRCRAFT =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId";
    public static final String SELECT_AIRCRAFT_BY_ID =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "WHERE aircrafts.aircraftId = ?";
    public static final String SELECT_AIRCRAFT_BY_TAIL_NUMBER =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "WHERE aircrafts.tailNumber = ?";
    public static final String SELECT_AIRCRAFTS_BY_NAME =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "WHERE aircrafts.aircraftName = ?";
    public static final String SELECT_AIRCRAFTS_BY_TYPE =
            "SELECT aircrafts.aircraftId, aircrafts.tailNumber, aircrafts.aircraftName, aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.aircraftTypeId = aircrafts.aircraftTypeId " +
                    "WHERE aircrafts.aircraftTypeId = ?";
    public static final String INSERT_AIRCRAFT =
            "INSERT INTO aircrafts (tailNumber, aircraftName, aircraftTypeId) VALUES (?,?,?)";
    public static final String UPDATE_AIRCRAFT =
            "UPDATE aircrafts SET tailNumber = ?, aircraftName = ?, aircraftTypeId = ? WHERE aircraftId = ?";
    public static final String REMOVE_AIRCRAFT_BY_ID =
            "DELETE FROM aircrafts WHERE aircraftId = ?"; // TODO: 20.10.2020 check

    private SqlQuery() {
    }
}
