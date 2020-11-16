package by.halatsevich.company.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AirportValidatorTest {

    @Test
    public void testIsValidAirportNameSuccess() {
        boolean condition = AirportValidator.isValidAirportName("Астана");
        assertTrue(condition);
    }

    @Test
    public void testIsValidAirportNameFailure() {
        boolean condition = AirportValidator.isValidAirportName("City 17123!!");
        assertFalse(condition);
    }

    @Test
    public void testIsValidCityOrCountrySuccess() {
        boolean condition = AirportValidator.isValidCityOrCountry("Шри-Ланка");
        assertTrue(condition);
    }

    @Test
    public void testIsValidCityOrCountryFailure() {
        boolean condition = AirportValidator.isValidCityOrCountry("112Париж");
        assertFalse(condition);
    }
}