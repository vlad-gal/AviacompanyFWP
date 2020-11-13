package by.halatsevich.company.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class PasswordEncryptionTest {

    @Test
    public void testEncryptPasswordSuccess() {
        String actual = PasswordEncryption.encryptPassword("password");
        String expected = "c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b";
        assertEquals(actual, expected);
    }

    @Test
    public void testEncryptPasswordFailure() {
        String actual = PasswordEncryption.encryptPassword("password");
        String expected = "c4e871eea12df589c6522e5f4";
        assertNotEquals(actual, expected);
    }
}