package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.entity.Airport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AirportCreatorTest {
    AirportCreator creator;
    Map<String, Object> airportParameter;

    @BeforeClass
    public void setUp() {
        creator = new AirportCreator();
        airportParameter = new HashMap<>();
        airportParameter.put(ColumnName.AIRPORT_ID, 1);
        airportParameter.put(ColumnName.AIRPORT_NAME, "Domodedovo");
        airportParameter.put(ColumnName.COUNTRY, "Russia");
        airportParameter.put(ColumnName.CITY, "Moscow");
    }

    @AfterClass
    public void tearDown() {
        creator = null;
        airportParameter = null;
    }

    @Test
    public void testCreateAirportSuccess() {
        Airport actual = creator.create(airportParameter);
        Airport expected = new Airport(1, "Russia", "Moscow", "Domodedovo");
        assertEquals(actual, expected);
    }

    @Test
    public void testCreateAirportFailure() {
        Airport actual = creator.create(airportParameter);
        Airport expected = new Airport(1, "Belarus", "Moscow", "Domodedovo");
        assertNotEquals(actual, expected);
    }
}