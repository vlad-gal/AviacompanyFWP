package by.halatsevich.company.model.dao;

public class ColumnName {
    //users
    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "roleId";
    public static final String STATUS_ID = "statusId";
    //personal data
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String TELEPHONE_NUMBER = "telephoneNumber";
    //statuses
    public static final String STATUS_NAME = "statusName";
    //roles
    public static final String ROLE_NAME = "roleName";
    //flights
    public static final String FLIGHT_ID = "flightId";
    public static final String DEPARTURE_AIRPORT_ID = "departureAirportId";
    public static final String DESTINATION_AIRPORT_ID = "destinationAirportId";
    public static final String DEPART_TIME = "departTime";
    public static final String ARRIVE_TIME = "arriveTime";
    public static final String AIRCRAFT_ID = "aircraftId";
    public static final String CREW_ID = "crewId";
    public static final String OPERATOR_ID = "operatorId";
    //crew
    public static final String DISPATCHER_ID = "dispatcherId";
    public static final String NUMBER_OF_PILOTS = "numberOfPilots";
    public static final String NUMBER_OF_NAVIGATORS = "numberOfNavigators";
    public static final String NUMBER_OF_RADIOMAN = "numberOfRadioman";
    public static final String NUMBER_OF_STEWARDESSES = "numberOfStewardesses";
    //airports
    public static final String AIRPORT_ID = "airportId";
    public static final String AIRPORT_NAME = "airportName";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    //aircrafts
    public static final String TAIL_NUMBER = "tailNumber";
    public static final String AIRCRAFT_NAME = "aircraftName";
    public static final String AIRCRAFT_TYPE_ID = "aircraftTypeId";
    //aircrafts type
    public static final String AIRCRAFT_TYPE = "aircraftType";

    private ColumnName() {
    }
}
