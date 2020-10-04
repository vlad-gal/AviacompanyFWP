package by.halatsevich.company.dao;

public class SqlQuery {
    public static final String SELECT_PASSWORD_BY_LOGIN = "SELECT users.login, users.password FROM users WHERE users.login = ?";
    public static final String SELECT_USER_BY_LOGIN = "SELECT users.login, users.email, personaldata.firstName, personaldata.lastName, " +
            "personaldata.telephoneNumber, roles.roleName, statuses.statusName FROM users JOIN roles " +
            "ON roles.roleId=users.roleId JOIN statuses ON statuses.statusId = users.statusId JOIN personaldata " +
            "ON personaldata.userId = users.userId WHERE users.login = ?";
    public static final String SELECT_ALL_AIRCRAFT = "SELECT aircrafts.tailNumber, aircrafts.aircraftName, " +
            "aircrafttypes.aircraftType FROM aircrafts JOIN aircrafttypes ON aircrafttypes.idAircraftTypes = aircrafts.aircraftType";
}
