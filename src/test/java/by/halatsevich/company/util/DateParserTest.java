package by.halatsevich.company.util;

import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class DateParserTest {

    @Test
    public void testParseDateSuccess() {
        Date actual = DateParser.parseDate("2020-11-05T20:48");
        Date expected = new Date(1604598480000L);
        assertEquals(actual, expected);
    }

    @Test
    public void testParseDateFailure() {
        Date actual = DateParser.parseDate("2020-11-05T20:48");
        Date expected = new Date(1L);
        assertNotEquals(actual, expected);
    }
}