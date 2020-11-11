package by.halatsevich.company.validator;

import by.halatsevich.company.model.entity.Status;

public abstract class BaseValidator {
    private static final String REGEX_ID = "[1-9]\\d*";

    public static boolean isValidId(String entityId) {
        return (entityId != null && entityId.matches(REGEX_ID));
    }

    public static boolean isValidStatus(String status) {
        boolean flag = false;
        if (status != null){
            try {
                Status.valueOf(status.toUpperCase());
                flag = true;
            } catch (IllegalArgumentException e) {
                flag = false;
            }
        }
        return flag;
    }
}
