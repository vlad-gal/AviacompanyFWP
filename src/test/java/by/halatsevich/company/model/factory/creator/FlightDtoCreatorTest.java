package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.FlightDto;
import by.halatsevich.company.entity.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class FlightDtoCreatorTest {
    FlightDtoCreator creator;
    Map<String, Object> flightDtoParameter;

    @BeforeClass
    public void setUp() {
        creator = new FlightDtoCreator();
        flightDtoParameter = new HashMap<>();
        flightDtoParameter.put(ColumnName.FLIGHT_ID, 1);
        flightDtoParameter.put(ColumnName.DEPARTURE_AIRPORT_ID, 2);
        flightDtoParameter.put(ColumnName.DESTINATION_AIRPORT_ID, 3);
        flightDtoParameter.put(ColumnName.DEPART_TIME, 123L);
        flightDtoParameter.put(ColumnName.ARRIVE_TIME, 345L);
        flightDtoParameter.put(ColumnName.AIRCRAFT_ID, 1);
        flightDtoParameter.put(ColumnName.OPERATOR_ID, 2);
        flightDtoParameter.put(ColumnName.CREW_ID, 4);
        flightDtoParameter.put(ColumnName.STATUS_NAME, "active".toUpperCase());
    }

    @AfterClass
    public void tearDown() {
        creator = null;
        flightDtoParameter = null;
    }

    @Test
    public void testCreateFlightDtoSuccess() {
        FlightDto actual = creator.create(flightDtoParameter);
        FlightDto expected = new FlightDto(1, 2, 3, 123L, 345L, 1, 4, 2, Status.ACTIVE);
        assertEquals(actual, expected);
    }

    @Test
    public void testCreateFlightDtoFailure() {
        FlightDto actual = creator.create(flightDtoParameter);
        FlightDto expected = new FlightDto(2, 3, 123, 345, 1, 4, 2, Status.ACTIVE);
        assertNotEquals(actual, expected);
    }
}