package by.halatsevich.company.validator;

import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {

    @DataProvider(name = "validAuthorizationData")
    public Object[][] validAuthorizationDate() {
        return new Object[][]{
                {new AuthorizationData("vladhala", "password")},
                {new AuthorizationData("oleg16_", "sdf;l__12")},
                {new AuthorizationData("igorasramov", "913383haU8")},
                {new AuthorizationData("fanat1968", "!riuaLKKdd")}
        };
    }

    @Test(dataProvider = "validAuthorizationData")
    public void testIsValidAuthorizationDataSuccess(AuthorizationData authorizationData) {
        boolean condition = UserValidator.isValidAuthorizationData(authorizationData);
        assertTrue(condition);
    }

    @DataProvider(name = "invalidAuthorizationData")
    public Object[][] invalidAuthorizationDate() {
        return new Object[][]{
                {new AuthorizationData("!vladhal!@@a", "password")},
                {new AuthorizationData("oleg16-/*", "sdf;l__12")},
                {new AuthorizationData("Привет", "")},
                {new AuthorizationData("fanat::1968", "!riuaLKKdd")}
        };
    }

    @Test(dataProvider = "invalidAuthorizationData")
    public void testIsValidAuthorizationDataFailure(AuthorizationData authorizationData) {
        boolean condition = UserValidator.isValidAuthorizationData(authorizationData);
        assertFalse(condition);
    }

    @DataProvider(name = "validRegistrationData")
    public Object[][] validRegistrationData() {
        return new Object[][]{
                {new RegistrationData("novikmish", "novikov@gmail.com", "password11", "Михаил", "Новиков", "378941635445", "admin", "active")},
                {new RegistrationData("vladhala", "halatsevich@gmail.com", "oret23^234", "Vlad", "Halatsevich", "375298163245", "operator", "busy")},
                {new RegistrationData("kornpetr", "kornpetya@gmail.com", "uReqQ239(", "Петр", "Корнеев", "897456123663", "navigator", "inactive")},
                {new RegistrationData("ivanovaL", "katya@gmail.com", "ratyA((*", "Екатерина", "Иванова", "645829733512", "stewardess", "fly")}
        };
    }

    @Test(dataProvider = "validRegistrationData")
    public void testIsValidRegistrationDataSuccess(RegistrationData registrationData) {
        boolean condition = UserValidator.isValidRegistrationData(registrationData);
        assertTrue(condition);
    }

    @DataProvider(name = "invalidRegistrationData")
    public Object[][] invalidRegistrationData() {
        return new Object[][]{
                {new RegistrationData("novikmi!sh", "novikov@gmail.com", "", "Михаил", "НОвиков", "378941635445", "", "ЙЦУ")},
                {new RegistrationData("vla", "halh@gmail.com", "^234", "Vlad", "Halatsevich", "375298163245", "operator", "busy")},
                {new RegistrationData("kor!", "kornpetya@gmail.com", "uReqQ239(", "Петр", "Корнеев", "897456123663", "navigator", "inactive")},
                {new RegistrationData("ivanovaL", "katyagmail.com", "ratyA((*", "Екатерина", "Иванова", "645829733512", "stewardess", "fly")}
        };
    }

    @Test(dataProvider = "invalidRegistrationData")
    public void testIsValidRegistrationDataFailure(RegistrationData registrationData) {
        boolean condition = UserValidator.isValidRegistrationData(registrationData);
        assertFalse(condition);
    }

    @Test
    public void testIsValidLoginSuccess() {
        boolean condition = UserValidator.isValidLogin("petrovVas");
        assertTrue(condition);
    }

    @Test
    public void testIsValidLoginFailure() {
        boolean condition = UserValidator.isValidLogin("!killer4987_");
        assertFalse(condition);
    }

    @Test
    public void testIsValidPasswordSuccess() {
        boolean condition = UserValidator.isValidPassword("PasWorD1!");
        assertTrue(condition);
    }

    @Test
    public void testIsValidPasswordFailure() {
        boolean condition = UserValidator.isValidPassword("  21312!3123 ");
        assertFalse(condition);
    }

    @Test
    public void testIsValidEmailSuccess() {
        boolean condition = UserValidator.isValidEmail("aviacompany.fwp@gmail.com");
        assertTrue(condition);
    }

    @Test
    public void testIsValidEmailFailure() {
        boolean condition = UserValidator.isValidEmail("a@re.ru");
        assertFalse(condition);
    }

    @Test
    public void testIsValidNameSuccess() {
        boolean condition = UserValidator.isValidName("Vlad");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNameFailure() {
        boolean condition = UserValidator.isValidName("DWarf");
        assertFalse(condition);
    }

    @Test
    public void testIsValidTelephoneNumberSuccess() {
        boolean condition = UserValidator.isValidTelephoneNumber("123456789123");
        assertTrue(condition);
    }

    @Test
    public void testIsValidTelephoneNumberFailure() {
        boolean condition = UserValidator.isValidTelephoneNumber("8163245");
        assertFalse(condition);
    }

    @Test
    public void testIsValidRoleSuccess() {
        boolean condition = UserValidator.isValidRole("Pilot");
        assertTrue(condition);
    }

    @Test
    public void testIsValidRoleFailure() {
        boolean condition = UserValidator.isValidRole("Main pilot");
        assertFalse(condition);
    }
}