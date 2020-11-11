package by.halatsevich.company.validator;

public abstract class CrewValidator extends BaseValidator{
    private static final String REGEX_CREW_NAME = "\\p{Alnum}{3,15}";
    private static final String REGEX_NUMBER_OF_STAFF = "[1-2]";
    private static final String REGEX_NUMBER_OF_STEWARDESSES = "\\d";

    public static boolean isValidCrewName(String crewName){
        return (crewName != null && crewName.matches(REGEX_CREW_NAME));
    }

    public static boolean isValidNumberOfPilots(String numberOfPilots){
        return (numberOfPilots != null && numberOfPilots.matches(REGEX_NUMBER_OF_STAFF));
    }

    public static boolean isValidNumberOfNavigators(String numberOfNavigators){
        return (numberOfNavigators != null && numberOfNavigators.matches(REGEX_NUMBER_OF_STAFF));
    }

    public static boolean isValidNumberOfRadioman(String numberOfRadioman){
        return (numberOfRadioman != null && numberOfRadioman.matches(REGEX_NUMBER_OF_STAFF));
    }

    public static boolean isValidNumberOfStewardesses(String numberOfStewardesses){
        return (numberOfStewardesses != null && numberOfStewardesses.matches(REGEX_NUMBER_OF_STEWARDESSES));
    }
}
