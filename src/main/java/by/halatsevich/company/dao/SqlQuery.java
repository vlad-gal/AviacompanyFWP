package by.halatsevich.company.dao;

public class SqlQuery {

    // user
    public static final String SELECT_ALL_USERS =
            "SELECT users.userId, users.email, users.password, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber" +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId" +
                    "JOIN personalData ON personalData.userId = users.userId";
    public static final String SELECT_USER_BY_LOGIN =
            "SELECT users.userId, users.email, users.password, users.login, " +
                    "statuses.statusName, " +
                    "roles.roleName, " +
                    "personalData.firstName, personalData.lastName, personalData.telephoneNumber" +
                    "FROM users " +
                    "JOIN roles ON roles.roleId = users.roleId " +
                    "JOIN statuses ON statuses.statusId = users.statusId" +
                    "JOIN personalData ON personalData.userId = users.userId " +
                    "WHERE users.login = ?";
    public static final String INSERT_USER =
            "INSERT INTO users (email, login, password, roleId, statusId)" +
                    "VALUES (?,?,?,?,?)";
    public static final String INSERT_PERSONAL_DATA =
            "INSERT INTO personalData (userId, firstName, lastName, telephoneNumber)" +
                    "VALUES (?,?,?,?)";
    public static final String REMOVE_USER_BY_ID =
            "UPDATE users SET statusId = '2' WHERE userId = ?";
    public static final String UPDATE_USER =
            "UPDATE users SET email = ?, login = ?, password = ?, roleId = ?, statusId = ? WHERE login = ?";
    public static final String UPDATE_PERSONAL_DATA =
            "UPDATE personalData SET firstName = ?, lastName = ?, telephoneNumber = ? WHERE userId = ?";


    // TODO: 08.10.2020 проверить работоспособность запросов

//flights
    // TODO: 08.10.2020 доделать связи таблиц flights crews и  statuses для реализации деактивации
    public static final String SELECT_ALL_FLIGHTS =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights";
    public static final String SELECT_FLIGHT_BY_DEPARTURE_AIRPORT_ID =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE departureAirportId = ?";
    public static final String SELECT_FLIGHT_BY_DESTINATION_AIRPORT_ID =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE destinationAirportId = ?";
    public static final String SELECT_FLIGHT_BY_DEPART_PERIOD_OF_TIME =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE (departTime >= ? AND departTime <= ?)";
    public static final String SELECT_FLIGHT_BY_ARRIVE_PERIOD_OF_TIME =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE (arriveTime >= ? AND arriveTime <= ?)";
    public static final String SELECT_FLIGHT_BY_CREW_ID =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE crewId = ?";
    public static final String SELECT_FLIGHT_BY_OPERATOR_ID =
            "SELECT flightId, departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId " +
                    "FROM flights WHERE operatorId = ?";
    public static final String INSERT_FLIGHT =
            "INSERT INTO flights (departureAirportId, destinationAirportId, departTime, arriveTime, operatorId, crewId) " +
                    "VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_FLIGHT =
            "UPDATE flights SET departureAirportId = ?, destinationAirportId = ?, departTime = ?, arriveTime = ?, " +
                    "operatorId = ?, crewId = ? WHERE flightId = ?";
    public static final String REMOVE_FLIGHT_BY_ID =
            "UPDATE flights SET statusId = '2' WHERE flightId = ?";


//crew
    public static final String SELECT_ALL_CREWS =
            "SELECT crewId, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses " +
                    "FROM crews";
    public static final String SELECT_CREW_BY_ID =
            "SELECT crewId, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses " +
                    "FROM crews WHERE crewId = ?";
    public static final String SELECT_CREW_BY_DISPATCHER_ID =
            "SELECT crewId, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses " +
                    "FROM crews WHERE dispatcherId = ?";
    public static final String SELECT_USERS_ID_BY_CREW_ID =
            "SELECT userId FROM crews_has_users WHERE crewId = ?";
    public static final String INSERT_CREW =
            "INSERT INTO crews (dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses) " +
            "VALUES (?,?,?,?,?)";
    public static final String INSERT_USER_INTO_CREW =
            "INSERT INTO crews_has_users (dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses) " +
            "VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER_INTO_CREW = "UPDATE crews_has_users SET userId = ? WHERE crewId = ?";
    public static final String UPDATE_DISPATCHER_INTO_CREW = "UPDATE crews SET dispatcherId = ? WHERE crewId = ?";
    public static final String UPDATE_CREW = "UPDATE crews SET dispatcherId = ?, numberOfPilots = ?, " +
            "numberOfNavigators = ?, numberOfRadioman = ?, numberOfStewardesses = ? WHERE crewId = ?";
    public static final String REMOVE_CREW_BY_ID = "UPDATE crews SET statusId = '2' WHERE crewId = ?";
    public static final String REMOVE_USER_FROM_CREW_BY_ID = "UPDATE crews_has_users SET userId = null WHERE (userId = ? AND crewId = ?)";;

//airport







    public static final String SELECT_ALL_AIRCRAFT =
            "SELECT aircrafts.tailNumber, aircrafts.aircraftName, " +
                    "aircrafttypes.aircraftType " +
                    "FROM aircrafts " +
                    "JOIN aircrafttypes ON aircrafttypes.idAircraftTypes = aircrafts.aircraftType";
}
