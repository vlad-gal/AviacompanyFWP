package by.halatsevich.company.validator;

/**
 * The class represents airport validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AirportValidator {
    private static final String REGEX_AIRPORT_NAME = "[A-Za-zА-Яа-я0-9\\s]{2,15}";
    private static final String REGEX_CITY_OR_COUNTRY = "[A-Za-zА-Яа-я\\-]{2,20}";

    private AirportValidator() {
    }

    /**
     * Check airport name is correct.
     *
     * @param airportName the airport name
     * @return true if airport name is correct, otherwise false
     */
    public static boolean isValidAirportName(String airportName) {
        return (airportName != null && airportName.matches(REGEX_AIRPORT_NAME));
    }

    /**
     * Check city or country is correct.
     *
     * @param cityOrCountry the city or country
     * @return true if city or country is correct, otherwise false
     */
    public static boolean isValidCityOrCountry(String cityOrCountry) {
        return (cityOrCountry != null && cityOrCountry.matches(REGEX_CITY_OR_COUNTRY));
    }
}
