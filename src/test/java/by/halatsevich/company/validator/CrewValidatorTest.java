package by.halatsevich.company.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CrewValidatorTest {

    @Test
    public void testIsValidCrewNameSuccess() {
        boolean condition = CrewValidator.isValidCrewName("TopTeam");
        assertTrue(condition);
    }

    @Test
    public void testIsValidCrewNameFailure() {
        boolean condition = CrewValidator.isValidCrewName("Орлы");
        assertFalse(condition);
    }

    @Test
    public void testIsValidNumberOfPilotsSuccess() {
        boolean condition = CrewValidator.isValidNumberOfPilots("1");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNumberOfPilotsFailure() {
        boolean condition = CrewValidator.isValidNumberOfPilots("3");
        assertFalse(condition);
    }

    @Test
    public void testIsValidNumberOfNavigatorsSuccess() {
        boolean condition = CrewValidator.isValidNumberOfNavigators("2");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNumberOfNavigatorsFailure() {
        boolean condition = CrewValidator.isValidNumberOfNavigators("0");
        assertFalse(condition);
    }

    @Test
    public void testIsValidNumberOfRadiomanSuccess() {
        boolean condition = CrewValidator.isValidNumberOfRadioman("1");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNumberOfRadiomanFailure() {
        boolean condition = CrewValidator.isValidNumberOfRadioman("4");
        assertFalse(condition);
    }

    @Test
    public void testIsValidNumberOfStewardessesSuccess() {
        boolean condition = CrewValidator.isValidNumberOfStewardesses("0");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNumberOfStewardessesFailure() {
        boolean condition = CrewValidator.isValidNumberOfStewardesses("19");
        assertFalse(condition);
    }
}