package by.halatsevich.company.model.service.validator;

import by.halatsevich.company.model.entity.AuthorizationData;
import by.halatsevich.company.model.entity.RegistrationData;

public class UserValidator {
    private static final String REGEX_LOGIN = "[\\w-]{3,40}";
    private static final String REGEX_PASSWORD = "[^\\s]{8,25}";
    private static final String REGEX_EMAIL = "[\\w-.]+@[a-zA-Z]+\\.[a-z]{2,6}";
    private static final String REGEX_TELEPHONE_NUMBER = "\\d{12}";
    private static final String REGEX_NAME = "[A-ZА-Я][a-zа-я]+";

    private UserValidator(){
    }

    public static boolean isValidAuthorizationData(AuthorizationData authorizationData) {
        String login = authorizationData.getLogin();
        String password = authorizationData.getPassword();
        return (login != null && password != null && login.matches(REGEX_LOGIN) && password.matches(REGEX_PASSWORD));
    }

    public static boolean isValidRegistrationData(RegistrationData registrationData) {
        String login = registrationData.getLogin();
        String password = registrationData.getPassword();
        String email = registrationData.getEmail();
        String firstName = registrationData.getFirstName();
        String lastName = registrationData.getLastName();
        String telephoneNumber = registrationData.getTelephoneNumber();
        boolean flag = false;
        if (login != null && password != null && email != null && firstName != null
                && lastName != null && telephoneNumber != null && login.matches(REGEX_LOGIN)
                && password.matches(REGEX_PASSWORD) && email.matches(REGEX_EMAIL) && firstName.matches(REGEX_NAME)
                && lastName.matches(REGEX_NAME) && telephoneNumber.matches(REGEX_TELEPHONE_NUMBER)) {
            flag = true;
        }
        return flag;
    }
}
