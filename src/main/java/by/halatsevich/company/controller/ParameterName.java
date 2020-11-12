package by.halatsevich.company.controller;

public class ParameterName {

    public static final String ROLE = "role";
    public static final String STATUS = "status";
    public static final String NOT_FOUND_COMMAND_MESSAGE = "message";
    public static final String INDEX_PATH = "index_path";
    public static final String STACKTRACE = "stacktrace";

    //common
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String TELEPHONE_NUMBER = "telephoneNumber";
    public static final String COMMAND = "command";
    public static final String ENCODING = "encoding";
    public static final String LANG = "lang";
    public static final String PREVIOUS_COMMAND = "previousCommand";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String ERROR_LOGIN_PASSWORD = "errorLoginPassword";
    public static final String EMPTY_REGISTRATION_DATA = "emptyRegistrationData";
    public static final String PASSWORD_DONT_MATCH = "passwordsDontMatch";
    public static final String INCORRECT_LOGIN = "incorrectLogin";

    //welcome page

    // flights
    public static final String FLIGHT_LIST = "flightList";
    public static final String FLIGHT_DEPARTURE_AIRPORT_NAME = "flight.departureAirport.airportName";
    public static final String FLIGHT_DEPARTURE_AIRPORT_CITY = "flight.departureAirport.city";
    public static final String FLIGHT_DEPARTURE_AIRPORT_COUNTRY = "flight.departureAirport.country";
    public static final String FLIGHT_DESTINATION_AIRPORT_NAME = "flight.destinationAirport.airportName";
    public static final String FLIGHT_DESTINATION_AIRPORT_CITY = "flight.destinationAirport.city";
    public static final String FLIGHT_DESTINATION_AIRPORT_COUNTRY = "flight.destinationAirport.country";
    public static final String FLIGHT_DEPART_TIME = "flight.departTime";
    public static final String FLIGHT_ARRIVE_TIME = "flight.arriveTime";
    public static final String EMPTY_FLIGHT_LIST = "emptyFlightList";
    public static final String ERROR_GET_FLIGHT_DATA_FROM_DB_FLAG = "errorGetFlightDataFromDBFlag";

    //aircraft
    public static final String AIRCRAFT_LIST = "aircraftList";
    public static final String AIRCRAFT_TAIL_NUMBER = "tailNumber";
    public static final String AIRCRAFT_NAME = "aircraftName";
    public static final String AIRCRAFT_TYPE = "aircraftType";
    public static final String EMPTY_AIRCRAFT_LIST_FLAG = "emptyAircraftListFlag";
    public static final String ERROR_GET_AIRCRAFT_DATA_FROM_DB = "errorGetAircraftDataFromDB";
    public static final String AIRPORT_COUNTRY = "country";

    //airports
    public static final String AIRPORT_LIST = "airportList";
    public static final String AIRPORT_NAME = "airportName";
    public static final String AIRPORT_CITY = "city";
    public static final String EMPTY_AIRPORT_LIST = "country";
    public static final String ERROR_GET_AIRPORT_DATA_FROM_DB = "errorGetAirportDataFromDB";

    //user
    public static final String USER_ID = "userId";
    public static final String USER_LOGIN = "userLogin";
    public static final String USER_FIRST_NAME = "userFirstName";
    public static final String USER_LAST_NAME = "userLastName";
    public static final String USER_ROLE = "userRole";
    public static final String USER_STATUS = "userStatus";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_TELEPHONE_NUMBER = "userTelephoneNumber";


    public static final String REQUEST_ATTRIBUTES = "requestAttributes";
    public static final String EMPTY_FLIGHT_LIST_FLAG = "emptyFlightListFlag";
    public static final String ERROR_GET_DATA_FROM_DB_FLAG = "errorGetDataFromDBFlag";
    public static final String EMPTY_LIST_FLAG = "emptyListFlag";
    public static final String USER = "user";
    public static final String ERROR_LOGIN_PASSWORD_FLAG = "errorLoginPasswordFlag";
    public static final String INACTIVE_USER_FLAG = "inactiveUserFlag";
    public static final String USER_ALREADY_EXIST_FLAG = "userAlreadyExistFlag";
    public static final String ERROR = "error";
    public static final String ERROR_VALIDATION_FLAG = "errorValidationFlag";
    public static final String ERROR_REGISTER_USER_FLAG = "errorRegisterUserFlag";
    public static final String REGISTRATION_DATA = "registrationData";


    // AircraftDetailPage
    public static final String AIRCRAFT_ID = "aircraftId";
    public static final String AIRCRAFT_NOT_FOUND_FLAG = "aircraftNotFoundFlag";
    public static final String AIRCRAFT = "aircraft";


    public static final String AIRPORT_ID = "airportId";
    public static final String AIRPORT = "airport";
    public static final String AIRPORT_NOT_FOUND_FLAG = "airportNotFoundFlag";
    public static final String FLIGHT_ID = "flightId";
    public static final String FLIGHT = "flight";
    public static final String FLIGHT_NOT_FOUND_FLAG = "flightNotFoundFlag";
    public static final String AUTHORIZATION_DATA = "authorizationData";
    public static final String USER_NOT_FOUND_FLAG = "userNotFoundFlag";
    public static final String EMAIL_NOT_EXIST_FLAG = "emailNotExistFlag";

    public static final String INCORRECT_EMAIL_FLAG = "incorrectEmailFlag";
    public static final String INCORRECT_PASSWORD_FLAG = "incorrectPasswordFlag";
    public static final String INCORRECT_ID_FLAG = "incorrectIdFlag";
    public static final String DIRECTION = "direction";
    public static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    public static final String PREVIOUS = "previous";
    public static final String NEXT = "next";
    public static final String UPDATING_SUCCESSFUL_FLAG = "updatingSuccessfulFlag";
    public static final String ERROR_UPDATE_USER_FLAG = "errorUpdateUserFlag";
    public static final String ERROR_UPDATE_PASSWORD_FLAG = "errorUpdatePasswordFlag";
    public static final String ROLES = "roles";
    public static final String REGISTRATION_SUCCESSFUL_FLAG = "registrationSuccessfulFlag";
    public static final String STATUSES = "statuses";
    public static final String UPDATING_USER = "updatingUser";
    public static final String INCORRECT_LOGIN_FLAG = "incorrectLoginFlag";
    public static final String ALL_USERS_LIST = "allUsersList";
    public static final String ALL_INACTIVE_USERS_LIST = "allInactiveUsersList";
    public static final String ALL_USERS_WITHOUT_ROLE_LIST = "allUsersWithoutRoleList";
    public static final String ALL_FLIGHTS_LIST = "allFlightsList";
    public static final String DEPARTURE_AIRPORT = "departureAirport";
    public static final String DESTINATION_AIRPORT = "destinationAirport";
    public static final String DEPART_TIME = "departTime";
    public static final String ARRIVE_TIME = "arriveTime";
    public static final String CREW = "crew";
    public static final String ERROR_CREATE_FLIGHT_FLAG = "errorCreateFlightFlag";
    public static final String CREATE_FLIGHT_SUCCESSFUL_FLAG = "createFlightSuccessfulFlag";
    public static final String AIRPORTS = "airports";
    public static final String AIRCRAFTS = "aircrafts";
    public static final String CREWS = "crews";
    public static final String OPERATORS = "operators";
    public static final String OPERATOR = "operator";
    public static final String DEPARTURE_AIRPORT_ERROR = "departureAirport";
    public static final String UPDATING_FLIGHT = "updatingFlight";
    public static final String ACTIVATION_SUCCESSFUL_FLAG = "activationSuccessfulFlag";
    public static final String RESET_LINK_SENT_SUCCESSFUL_FLAG = "resetLinkSentSuccessfulFlag";
    public static final String SHOW_USERS_FLAG = "showUsersFlag";
    public static final String SHOW_FLIGHTS_FLAG = "showFlightsFlag";
    public static final String ERROR_UPDATE_FLIGHT_FLAG = "errorUpdateFlightFlag";
    public static final String CREW_LIST = "crewList";
    public static final String ALL_CREW_LIST = "allCrewList";
    public static final String SHOW_CREWS_FLAG = "showCrewsFlag";
    public static final String CREW_NAME = "crewName";
    public static final String NUMBER_OF_PILOTS = "numberOfPilots";
    public static final String NUMBER_OF_NAVIGATORS = "numberOfNavigators";
    public static final String NUMBER_OF_RADIOMAN = "numberOfRadioman";
    public static final String NUMBER_OF_STEWARDESSES = "numberOfStewardesses";


    public static final String CREATE_CREW_SUCCESSFUL_FLAG = "createCrewSuccessfulFlag";
    public static final String ERROR_CREATE_CREW_FLAG = "errorCreateCrewFlag";
    public static final String CREW_NOT_FOUND_FLAG = "crewNotFoundFlag";
    public static final String CREW_DTO = "crewDto";
    public static final String ADDING_USER = "addingUser";
    public static final String CREW_ID = "crewId";
    public static final String ADD_USER_INTO_CREW_SUCCESSFUL_FLAG = "addUserIntoCrewSuccessfulFlag";
    public static final String AVAILABLE_PLACES_IN_CREW = "availablePlacesInCrew";
    public static final String AVAILABLE_PLACES_IN_CREW_FLAG = "availablePlacesInCrewFlag";
    public static final String ERROR_ADD_USER_INTO_CREW_FLAG = "errorAddUserIntoCrewFlag";
    public static final String PILOTS = "pilots";

    public static final String NAVIGATORS = "navigators";
    public static final String RADIOMANS = "radiomans";
    public static final String STEWARDESSES = "stewardesses";
    public static final String DISPATCHER = "dispatcher";
    public static final String SHOW_CREW_FLAG = "showCrewFlag";
    public static final String UPDATING_CREW = "updatingCrew";
    public static final String ERROR_UPDATE_CREW_FLAG = "errorUpdateCrewFlag";
    public static final String CREATE_AIRPORT_SUCCESSFUL_FLAG = "createAirportSuccessfulFlag";
    public static final String ERROR_CREATE_AIRPORT_FLAG = "errorCreateAirportFlag";
    public static final String ALL_AIRPORT_LIST = "allAirportList";
    public static final String SHOW_AIRPORTS_FLAG = "showAirportsFlag";
    public static final String UPDATING_AIRPORT = "updatingAirport";
    public static final String UPDATE_AIRPORT_SUCCESSFUL_FLAG = "updateAirportSuccessfulFlag";
    public static final String ERROR_UPDATE_AIRPORT_FLAG = "errorUpdateAirportFlag";
    public static final String SHOW_AIRCRAFTS_FLAG = "showAircraftsFlag";
    public static final String AIRCRAFT_TYPES = "aircraftTypes";
    public static final String CREATE_AIRCRAFT_SUCCESSFUL_FLAG = "createAircraftSuccessfulFlag";
    public static final String ERROR_CREATE_AIRCRAFT_FLAG = "errorCreateAircraftFlag";
    public static final String UPDATING_AIRCRAFT = "updatingAircraft";
    public static final String UPDATE_AIRCRAFT_SUCCESSFUL_FLAG = "updateAircraftSuccessfulFlag";
    public static final String ERROR_UPDATE_AIRCRAFT_FLAG = "errorUpdateAircraftFlag";
}
