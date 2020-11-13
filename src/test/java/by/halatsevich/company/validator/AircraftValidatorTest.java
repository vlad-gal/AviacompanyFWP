package by.halatsevich.company.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AircraftValidatorTest {

    @Test
    public void testIsValidTailNumberSuccess() {
        boolean condition = AircraftValidator.isValidAircraftName("ABCQW11");
        assertTrue(condition);
    }

    @Test
    public void testIsValidTailNumberFailure() {
        boolean condition = AircraftValidator.isValidAircraftName("hello!1");
        assertFalse(condition);
    }

    @Test
    public void testIsValidAircraftNameSuccess() {
        boolean condition = AircraftValidator.isValidAircraftName("Airbus A320-100/200");
        assertTrue(condition);
    }

    @Test
    public void testIsValidAircraftNameFailure() {
        boolean condition = AircraftValidator.isValidAircraftName("Airbus A320_100/200");
        assertFalse(condition);
    }

    @Test
    public void testIsValidAircraftTypeSuccess() {
        boolean condition = AircraftValidator.isValidAircraftType("cargo");
        assertTrue(condition);
    }

    @Test
    public void testIsValidAircraftTypeFailure() {
        boolean condition = AircraftValidator.isValidAircraftType("Military");
        assertFalse(condition);
    }
}