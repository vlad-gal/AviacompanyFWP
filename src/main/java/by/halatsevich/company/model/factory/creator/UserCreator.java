package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.entity.User;
import by.halatsevich.company.model.factory.EntityCreator;

import java.util.Map;

/**
 * The class represents user creator implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UserCreator implements EntityCreator<User> {

    @Override
    public User create(Map<String, Object> userParameter) {
        int userId = (int) userParameter.get(ColumnName.USER_ID);
        String email = (String) userParameter.get(ColumnName.EMAIL);
        String firstName = (String) userParameter.get(ColumnName.FIRST_NAME);
        String lastName = (String) userParameter.get(ColumnName.LAST_NAME);
        long telephoneNumber = (long) userParameter.get(ColumnName.TELEPHONE_NUMBER);
        String login = (String) userParameter.get(ColumnName.LOGIN);
        String statusName = (String) userParameter.get(ColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        String roleName = (String) userParameter.get(ColumnName.ROLE_NAME);
        User.Role role = User.Role.valueOf(roleName);
        return new User(userId, email, login, firstName, lastName, telephoneNumber, role, status);
    }
}
