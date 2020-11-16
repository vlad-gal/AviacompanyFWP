package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.entity.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class UserCreatorTest {
    UserCreator creator;
    Map<String, Object> userParameter;

    @BeforeClass
    public void setUp() {
        creator = new UserCreator();
        userParameter = new HashMap<>();
        userParameter.put(ColumnName.USER_ID, 1);
        userParameter.put(ColumnName.EMAIL, "email@mail.ru");
        userParameter.put(ColumnName.LOGIN, "login");
        userParameter.put(ColumnName.STATUS_NAME, "active".toUpperCase());
        userParameter.put(ColumnName.ROLE_NAME, "admin".toUpperCase());
        userParameter.put(ColumnName.FIRST_NAME, "Misha");
        userParameter.put(ColumnName.LAST_NAME, "Novik");
        userParameter.put(ColumnName.TELEPHONE_NUMBER, 375294567891L);
    }

    @AfterClass
    public void tearDown() {
        creator = null;
        userParameter = null;
    }

    @Test
    public void testCreateUserSuccess() {
        User actual = creator.create(userParameter);
        User expected = new User(1, "email@mail.ru", "login", "Misha", "Novik", 375294567891L, User.Role.ADMIN, Status.ACTIVE);
        assertEquals(actual, expected);
    }

    @Test
    public void testCreateUserFailure() {
        User actual = creator.create(userParameter);
        User expected = new User("email@mail.ru", "login", "Misha", "Novik", 375294567891L, User.Role.ADMIN, Status.ACTIVE);
        assertNotEquals(actual, expected);
    }
}