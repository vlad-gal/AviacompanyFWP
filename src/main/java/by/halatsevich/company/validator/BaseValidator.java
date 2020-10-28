package by.halatsevich.company.validator;

public class BaseValidator {
    private static final String REGEX_ID = "[1-9]\\d*";

    public static boolean isValidId(String entityId) {
        return (entityId != null && entityId.matches(REGEX_ID));
    }

}
