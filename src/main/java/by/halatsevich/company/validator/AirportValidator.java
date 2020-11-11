package by.halatsevich.company.validator;

public abstract class AirportValidator {
    private static final String REGEX_AIRPORT_NAME = "[A-Za-zА-Яа-я0-9]{2,15}";
    private static final String REGEX_CITY_OR_COUNTRY = "[A-Za-zА-Яа-я\\-]{2,20}";

    public static boolean isValidAirportName(String airportName) {
        return (airportName != null && airportName.matches(REGEX_AIRPORT_NAME));
    }

    public static boolean isValidCityOrCountry(String cityOrCountry) {
        return (cityOrCountry != null && cityOrCountry.matches(REGEX_CITY_OR_COUNTRY));
    }
}
