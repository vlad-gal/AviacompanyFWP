package by.halatsevich.company.validator;

import by.halatsevich.company.entity.Status;

/**
 * The class represents base validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class BaseValidator {
    private static final String REGEX_ID = "[1-9]\\d*";

    private BaseValidator() {
    }

    /**
     * Check id is correct.
     *
     * @param entityId the entity id
     * @return true if id is correct, otherwise false
     */
    public static boolean isValidId(String entityId) {
        return (entityId != null && entityId.matches(REGEX_ID));
    }

    /**
     * Check status is correct.
     *
     * @param status the status
     * @return true if status is present in Status class, otherwise false
     */
    public static boolean isValidStatus(String status) {
        boolean flag = false;
        if (status != null) {
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
