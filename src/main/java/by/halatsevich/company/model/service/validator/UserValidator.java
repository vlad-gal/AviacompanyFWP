package by.halatsevich.company.model.service.validator;

import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;

import java.util.regex.Pattern;

public class UserValidator {
    private static final String REGEX_LOGIN = "[\\w-]{3,40}";
    private static final String REGEX_PASSWORD = "[^\\s]{8,25}";
    private static final String REGEX_EMAIL = "[]{}";
    private static final String REGEX_TELEPHONE_NUMBER = "//d{12}";
    private static final String REGEX_NAME = "//w+";



    public static boolean isValidAuthorizationData(AuthorizationData authorizationData) {
        String login = authorizationData.getLogin();
        String password = authorizationData.getPassword();
        return (login != null && password != null && login.matches(REGEX_LOGIN) && password.matches(REGEX_PASSWORD));
    }

    public static boolean isValidRegistrationData(RegistrationData registrationData) {
        // TODO: 17.10.2020
        String login = registrationData.getLogin();
        String password = registrationData.getPassword();
        String email = registrationData.getEmail();
        String firstName = registrationData.getFirstName();
        String lastName = registrationData.getLastName();
        String telephoneNumber = registrationData.getTelephoneNumber();
        return (login != null && password != null && login.matches(REGEX_LOGIN) && password.matches(REGEX_PASSWORD));
    }

    public static boolean isValidLogin(String login) {
        Pattern p = Pattern.compile(REGEX_LOGIN);
        login.matches(REGEX_LOGIN);
        return true;
    }

    public static boolean isValidPassword(String password) {

        return true;
    }

    public static boolean isValidEmail(String email) {

        return true;
    }

    public static boolean isValidTelephoneNumber(String telephoneNumber) {

        return true;
    }

    public static boolean isValidFirstName(String firstName) {
        return false;
    }

    public static boolean isValidLastName(String lastName) {
        return false;
    }

    public static boolean isValidUserLoginPass(String login, String password, String foundLogin, String foundPassword) {
        return login.equals(foundLogin) && password.equals(foundPassword);
    }
}
