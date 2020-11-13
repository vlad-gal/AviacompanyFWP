package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AircraftCreatorTest {
    AircraftCreator creator;
    Map<String, Object> aircraftParameter;

    @BeforeClass
    public void setUp() {
        creator = new AircraftCreator();
        aircraftParameter = new HashMap<>();
        aircraftParameter.put(ColumnName.AIRCRAFT_ID, 1);
        aircraftParameter.put(ColumnName.TAIL_NUMBER, "OEV43");
        aircraftParameter.put(ColumnName.AIRCRAFT_NAME, "Boeing");
        aircraftParameter.put(ColumnName.AIRCRAFT_TYPE, "Cargo".toUpperCase());
        aircraftParameter.put(ColumnName.STATUS_NAME, "active".toUpperCase());
    }

    @AfterClass
    public void tearDown() {
        creator = null;
        aircraftParameter = null;
    }

    @Test
    public void testCreateAircraftSuccessful() {
        Aircraft actual = creator.create(aircraftParameter);
        Aircraft expected = new Aircraft(1, "OEV43", "Boeing", Aircraft.AircraftType.CARGO, Status.ACTIVE);
        assertEquals(actual, expected);
    }

    @Test
    public void testCreateAircraftFailure() {
        Aircraft actual = creator.create(aircraftParameter);
        Aircraft expected = new Aircraft(2, "OEV43", "Boeing", Aircraft.AircraftType.CARGO, Status.ACTIVE);
        assertNotEquals(actual, expected);
    }
}