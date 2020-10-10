package by.halatsevich.company.dao.factory;

import by.halatsevich.company.dao.factory.creator.*;
import by.halatsevich.company.entity.*;

public class EntityFactory {
    private static final EntityFactory instance = new EntityFactory();
    private final EntityCreator<User> userCreator = new UserCreator();
    private final EntityCreator<Flight> flightCreator = new FlightCreator();
    private final EntityCreator<Crew> crewCreator = new CrewCreator();
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

    public EntityCreator<Flight> getFlightCreator() {
        return flightCreator;
    }

    public EntityCreator<Crew> getCrewCreator() {
        return crewCreator;
    }

    public EntityCreator<Airport> getAirportCreator() {
        return airportCreator;
    }

    public EntityCreator<Aircraft> getAircraftCreator() {
        return aircraftCreator;
    }
}
