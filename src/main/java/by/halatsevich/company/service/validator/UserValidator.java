package by.halatsevich.company.service.validator;

public class UserValidator {

    public static boolean isValidUserLoginPass(String login, String password, String foundLogin, String foundPassword) {
        return login.equals(foundLogin) && password.equals(foundPassword);
    }
}
