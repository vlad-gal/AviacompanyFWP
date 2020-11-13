package by.halatsevich.company.validator;

/**
 * The class represents crew validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CrewValidator {
    private static final String REGEX_CREW_NAME = "\\p{Alnum}{3,15}";
    private static final String REGEX_NUMBER_OF_STAFF = "[1-2]";
    private static final String REGEX_NUMBER_OF_STEWARDESSES = "\\d";

    private CrewValidator() {
    }

    /**
     * Check crew name is correct.
     *
     * @param crewName the crew name
     * @return true if crew name is correct, otherwise false
     */
    public static boolean isValidCrewName(String crewName) {
        return (crewName != null && crewName.matches(REGEX_CREW_NAME));
    }

    /**
     * Check number of pilots is correct.
     *
     * @param numberOfPilots the number of pilots
     * @return true if number of pilots is correct, otherwise false
     */
    public static boolean isValidNumberOfPilots(String numberOfPilots) {
        return (numberOfPilots != null && numberOfPilots.matches(REGEX_NUMBER_OF_STAFF));
    }

    /**
     * Check number of navigators is correct.
     *
     * @param numberOfNavigators the number of navigators
     * @return true if number of navigators is correct, otherwise false
     */
    public static boolean isValidNumberOfNavigators(String numberOfNavigators) {
        return (numberOfNavigators != null && numberOfNavigators.matches(REGEX_NUMBER_OF_STAFF));
    }

    /**
     * Check number of radioman is correct.
     *
     * @param numberOfRadioman the number of radioman
     * @return true if number of radioman is correct, otherwise false
     */
    public static boolean isValidNumberOfRadioman(String numberOfRadioman) {
        return (numberOfRadioman != null && numberOfRadioman.matches(REGEX_NUMBER_OF_STAFF));
    }

    /**
     * Check number of stewardesses is correct.
     *
     * @param numberOfStewardesses the number of stewardesses
     * @return true if number of stewardesses is correct, otherwise false
     */
    public static boolean isValidNumberOfStewardesses(String numberOfStewardesses) {
        return (numberOfStewardesses != null && numberOfStewardesses.matches(REGEX_NUMBER_OF_STEWARDESSES));
    }
}
