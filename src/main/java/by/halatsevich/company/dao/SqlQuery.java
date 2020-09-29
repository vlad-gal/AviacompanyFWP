package by.halatsevich.company.dao;

public class SqlQuery {
    public static final String SELECT_ALL_USERS = "SELECT users.userId, users.email, users.login, users.password, roles.roleName, statuses.statusName FROM users JOIN roles ON roles.roleId=users.roleId";
}
