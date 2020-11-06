package by.halatsevich.company.util;

import java.time.*;
import java.util.Date;

public class DateParser {

    private DateParser(){}

    public static Date parseDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }
}
