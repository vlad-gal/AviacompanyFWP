package by.halatsevich.company.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BaseValidatorTest {

    @Test
    public void testIsValidIdSuccess() {
        boolean condition = BaseValidator.isValidId("123");
        assertTrue(condition);
    }

    @Test
    public void testIsValidIdFailure() {
        boolean condition = BaseValidator.isValidId("0");
        assertFalse(condition);
    }

    @Test
    public void testIsValidStatusSuccess(){
        boolean condition = BaseValidator.isValidStatus("fLy");
        assertTrue(condition);
    }

    @Test
    public void testIsValidStatusFailure() {
        boolean condition = BaseValidator.isValidStatus("In progress");
        assertFalse(condition);
    }
}