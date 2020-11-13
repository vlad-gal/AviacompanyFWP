package by.halatsevich.company.validator;

/**
 * The class represents flight validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class FlightValidator {
    private static final String REGEX_TIME =
            "((19[7-9]\\d)|(2\\d{3}))-((0[1-9])|(1[0-2]))-(([0-2]\\d)|([3][0-1]))T((([0-1]\\d)|([2][0-4]))[:][0-5]\\d)";

    private FlightValidator() {
    }

    /**
     * Check time format is correct.
     *
     * @param time the time
     * @return true if time is correct, otherwise false
     */
    public static boolean isValidTime(String time) {
        return (time != null && time.matches(REGEX_TIME));
    }
}
