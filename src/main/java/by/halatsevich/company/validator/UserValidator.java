package by.halatsevich.company.validator;

import by.halatsevich.company.entity.AuthorizationData;
import by.halatsevich.company.entity.RegistrationData;
import by.halatsevich.company.entity.User;

/**
 * The class represents user validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UserValidator {
    private static final String REGEX_LOGIN = "[\\w-]{3,40}";
    private static final String REGEX_PASSWORD = "[^\\s]{8,25}";
    private static final String REGEX_EMAIL = "[\\w-.]{3,20}@[a-zA-Zа-яА-Я]{2,14}\\.[a-zа-я]{2,6}";
    private static final String REGEX_TELEPHONE_NUMBER = "\\d{12}";
    private static final String REGEX_NAME = "[A-ZА-Я][a-zа-я]{1,14}";

    private UserValidator() {
    }

    /**
     * Check authorization data is correct.
     *
     * @param authorizationData the authorization data
     * @return true if authorization data is correct, otherwise false
     */
    public static boolean isValidAuthorizationData(AuthorizationData authorizationData) {
        boolean isCorrect = true;
        if (!isValidLogin(authorizationData.getLogin())) {
            isCorrect = false;
            authorizationData.setLogin(null);
        }
        if (!isValidPassword(authorizationData.getPassword())) {
            isCorrect = false;
            authorizationData.setPassword(null);
        }
        return isCorrect;
    }

    /**
     * Check registration data is correct.
     *
     * @param registrationData the registration data
     * @return true if registration data is correct, otherwise false
     */
    public static boolean isValidRegistrationData(RegistrationData registrationData) {
        boolean isCorrect = true;
        if (!isValidLogin(registrationData.getLogin())) {
            isCorrect = false;
            registrationData.setLogin(null);
        }
        if (!isValidEmail(registrationData.getEmail())) {
            isCorrect = false;
            registrationData.setEmail(null);
        }
        if (!isValidPassword(registrationData.getPassword())) {
            isCorrect = false;
            registrationData.setPassword(null);
        }
        if (!isValidName(registrationData.getFirstName())) {
            isCorrect = false;
            registrationData.setFirstName(null);
        }
        if (!isValidName(registrationData.getLastName())) {
            isCorrect = false;
            registrationData.setLastName(null);
        }
        if (!isValidTelephoneNumber(registrationData.getTelephoneNumber())) {
            isCorrect = false;
            registrationData.setTelephoneNumber(null);
        }
        if (!isValidRole(registrationData.getRole())) {
            isCorrect = false;
            registrationData.setRole(null);
        }
        if (!BaseValidator.isValidStatus(registrationData.getStatus())) {
            isCorrect = false;
            registrationData.setRole(null);
        }
        return isCorrect;
    }

    /**
     * Check login is correct.
     *
     * @param login the login
     * @return true if login is correct, otherwise false
     */
    public static boolean isValidLogin(String login) {
        return (login != null && login.matches(REGEX_LOGIN));
    }

    /**
     * Check password is correct.
     *
     * @param password the password
     * @return true if password is correct, otherwise false
     */
    public static boolean isValidPassword(String password) {
        return (password != null && password.matches(REGEX_PASSWORD));
    }

    /**
     * Check email is correct.
     *
     * @param email the email
     * @return true if email is correct, otherwise false
     */
    public static boolean isValidEmail(String email) {
        return (email != null && email.matches(REGEX_EMAIL));
    }

    /**
     * Check name is correct.
     *
     * @param name the name
     * @return true if name is correct, otherwise false
     */
    public static boolean isValidName(String name) {
        return (name != null && name.matches(REGEX_NAME));
    }

    /**
     * Check telephone number is correct.
     *
     * @param telephoneNumber the telephone number
     * @return true if telephone number is correct, otherwise false
     */
    public static boolean isValidTelephoneNumber(String telephoneNumber) {
        return (telephoneNumber != null && telephoneNumber.matches(REGEX_TELEPHONE_NUMBER));
    }

    /**
     * Check role is correct.
     *
     * @param role the role
     * @return true if aircraft type is present in User.Role class, otherwise false
     */
    public static boolean isValidRole(String role) {
        boolean flag = false;
        if (role != null) {
            try {
                User.Role.valueOf(role.toUpperCase());
                flag = true;
            } catch (IllegalArgumentException e) {
                flag = false;
            }
        }
        return flag;
    }
}
