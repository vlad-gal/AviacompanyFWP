package by.halatsevich.company.validator;

import by.halatsevich.company.model.entity.Aircraft;

public class AircraftValidator {
    private static final String REGEX_TAIL_NUMBER = "[A-Z0-9]{2,10}";
    private static final String REGEX_AIRCRAFT_NAME = "[A-Za-z0-9/\\-\\s]{2,40}";

    public static boolean isValidTailNumber(String tailNumber) {
        return (tailNumber != null && tailNumber.matches(REGEX_TAIL_NUMBER));
    }

    public static boolean isValidAircraftName(String aircraftName) {
        return (aircraftName != null && aircraftName.matches(REGEX_AIRCRAFT_NAME));
    }

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
