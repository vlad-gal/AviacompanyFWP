package by.halatsevich.company.model.dao;

/**
 * The class represents column name in tables.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class ColumnName {
    /**
     * Constants for users table
     */
    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";

    /**
     * Constants for personal data table
     */
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String TELEPHONE_NUMBER = "telephoneNumber";

    /**
     * Constants for statuses table
     */
    public static final String STATUS_NAME = "statusName";

    /**
     * Constants for roles table
     */
    public static final String ROLE_NAME = "roleName";

    /**
     * Constants for flights table
     */
    public static final String FLIGHT_ID = "flightId";
    public static final String DEPARTURE_AIRPORT_ID = "departureAirportId";
    public static final String DESTINATION_AIRPORT_ID = "destinationAirportId";
    public static final String DEPART_TIME = "departTime";
    public static final String ARRIVE_TIME = "arriveTime";
    public static final String OPERATOR_ID = "operatorId";

    /**
     * Constants for aircrafts table
     */
    public static final String AIRCRAFT_ID = "aircraftId";
    public static final String TAIL_NUMBER = "tailNumber";
    public static final String AIRCRAFT_NAME = "aircraftName";
    public static final String AIRCRAFT_TYPE = "aircraftType";

    /**
     * Constants for crews table
     */
    public static final String CREW_ID = "crewId";
    public static final String CREW_NAME = "crewName";
    public static final String DISPATCHER_ID = "dispatcherId";
    public static final String NUMBER_OF_PILOTS = "numberOfPilots";
    public static final String NUMBER_OF_NAVIGATORS = "numberOfNavigators";
    public static final String NUMBER_OF_RADIOMAN = "numberOfRadioman";
    public static final String NUMBER_OF_STEWARDESSES = "numberOfStewardesses";

    /**
     * Constants for airports table
     */
    public static final String AIRPORT_ID = "airportId";
    public static final String AIRPORT_NAME = "airportName";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";

    private ColumnName() {
    }
}
