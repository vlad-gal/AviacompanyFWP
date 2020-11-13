package by.halatsevich.company.model.factory;

import by.halatsevich.company.model.entity.*;
import by.halatsevich.company.model.factory.creator.*;

/**
 * The class represents entity factory.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class EntityFactory {
    private static final EntityFactory instance = new EntityFactory();
    private final EntityCreator<User> userCreator = new UserCreator();
    private final EntityCreator<FlightDto> flightDtoCreator = new FlightDtoCreator();
    private final EntityCreator<CrewDto> crewDtoCreator = new CrewDtoCreator();
    private final EntityCreator<Airport> airportCreator = new AirportCreator();
    private final EntityCreator<Aircraft> aircraftCreator = new AircraftCreator();

    private EntityFactory() {
    }

    /**
     * Gets factory instance.
     *
     * @return the instance
     */
    public static EntityFactory getInstance() {
        return instance;
    }

    /**
     * Gets user creator.
     *
     * @return the user creator
     */
    public EntityCreator<User> getUserCreator() {
        return userCreator;
    }

    /**
     * Gets flight dto creator.
     *
     * @return the flight dto creator
     */
    public EntityCreator<FlightDto> getFlightDtoCreator() {
        return flightDtoCreator;
    }

    /**
     * Gets crew dto creator.
     *
     * @return the crew dto creator
     */
    public EntityCreator<CrewDto> getCrewDtoCreator() {
        return crewDtoCreator;
    }

    /**
     * Gets airport creator.
     *
     * @return the airport creator
     */
    public EntityCreator<Airport> getAirportCreator() {
        return airportCreator;
    }

    /**
     * Gets aircraft creator.
     *
     * @return the aircraft creator
     */
    public EntityCreator<Aircraft> getAircraftCreator() {
        return aircraftCreator;
    }
}
