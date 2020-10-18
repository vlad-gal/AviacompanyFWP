package by.halatsevich.company.model.factory;

import by.halatsevich.company.model.factory.creator.*;
import by.halatsevich.company.model.entity.*;

public class EntityFactory {
    private static final EntityFactory instance = new EntityFactory();
    private final EntityCreator<User> userCreator = new UserCreator();
    private final EntityCreator<FlightDto> flightDtoCreator = new FlightDtoCreator();
    private final EntityCreator<CrewDto> crewDtoCreator = new CrewDtoCreator();
    private final EntityCreator<Airport> airportCreator = new AirportCreator();
    private final EntityCreator<Aircraft> aircraftCreator = new AircraftCreator();

    private EntityFactory() {
    }

    public static EntityFactory getInstance() {
        return instance;
    }

    public EntityCreator<User> getUserCreator() {
        return userCreator;
    }

    public EntityCreator<FlightDto> getFlightDtoCreator() {
        return flightDtoCreator;
    }

    public EntityCreator<CrewDto> getCrewDtoCreator() {
        return crewDtoCreator;
    }

    public EntityCreator<Airport> getAirportCreator() {
        return airportCreator;
    }

    public EntityCreator<Aircraft> getAircraftCreator() {
        return aircraftCreator;
    }
}