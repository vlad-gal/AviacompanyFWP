package by.halatsevich.company.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * The class represents date parser.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class DateParser {

    private DateParser() {
    }

    /**
     * Parse date.
     *
     * @param date the date
     * @return the parsed date
     */
    public static Date parseDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }
}
