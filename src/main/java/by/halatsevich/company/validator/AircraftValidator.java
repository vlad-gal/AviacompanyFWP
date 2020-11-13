package by.halatsevich.company.validator;

import by.halatsevich.company.model.entity.Aircraft;

/**
 * The class represents aircraft validator.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AircraftValidator {
    private static final String REGEX_TAIL_NUMBER = "[A-Z0-9]{2,10}";
    private static final String REGEX_AIRCRAFT_NAME = "[A-Za-z0-9/\\-\\s]{2,40}";

    private AircraftValidator() {
    }

    /**
     * Check tail number is correct.
     *
     * @param tailNumber the tail number
     * @return true if tail number is correct, otherwise false
     */
    public static boolean isValidTailNumber(String tailNumber) {
        return (tailNumber != null && tailNumber.matches(REGEX_TAIL_NUMBER));
    }

    /**
     * Check aircraft name is correct.
     *
     * @param aircraftName the aircraft name
     * @return true if aircraft name is correct, otherwise false
     */
    public static boolean isValidAircraftName(String aircraftName) {
        return (aircraftName != null && aircraftName.matches(REGEX_AIRCRAFT_NAME));
    }

    /**
     * Check aircraft type is correct.
     *
     * @param aircraftType the aircraft type
     * @return true if aircraft type is present in Aircraft.AircraftType class, otherwise false
     */
    public static boolean isValidAircraftType(String aircraftType) {
        boolean flag = false;
        if (aircraftType != null) {
            try {
                Aircraft.AircraftType.valueOf(aircraftType.toUpperCase());
                flag = true;
            } catch (IllegalArgumentException e) {
                flag = false;
            }
        }
        return flag;
    }
}
