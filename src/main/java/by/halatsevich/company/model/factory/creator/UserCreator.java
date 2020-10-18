package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.SqlColumnName;
import by.halatsevich.company.model.factory.EntityCreator;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.entity.UserData;

import java.util.Map;

public class UserCreator implements EntityCreator<User> {

    @Override
    public User create(Map<String, Object> userParameter) {
        int userId = (int) userParameter.get(SqlColumnName.USER_ID);
        String email = (String) userParameter.get(SqlColumnName.EMAIL);
        String password = (String) userParameter.get(SqlColumnName.PASSWORD);
        String login = (String) userParameter.get(SqlColumnName.LOGIN);
        String statusName = (String) userParameter.get(SqlColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        String roleName = (String) userParameter.get(SqlColumnName.ROLE_NAME);
        User.Role role = User.Role.valueOf(roleName);
        String firstName = (String) userParameter.get(SqlColumnName.FIRST_NAME);
        String lastName = (String) userParameter.get(SqlColumnName.LAST_NAME);
        long telephoneNumber = (long) userParameter.get(SqlColumnName.TELEPHONE_NUMBER);
        UserData userData = new UserData(userId, firstName, lastName, telephoneNumber);
        return new User(userId, email, login, password, role, status, userData);
    }
}
