package by.halatsevich.company.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FlightValidatorTest {

    @Test
    public void testIsValidTimeSuccess() {
        boolean condition = FlightValidator.isValidTime("2024-12-05T22:48");
        assertTrue(condition);
    }

    @Test
    public void testIsValidTimeFailure() {
        boolean condition = FlightValidator.isValidTime("2024-12-05 22:48");
        assertFalse(condition);
    }
}