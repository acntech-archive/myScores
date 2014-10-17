package myscores.datetime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTimeParser {

    private DateTimeParser() {
    }

    public static DateTime parse(String string) {
        return parse(string, ISODateTimeFormat.dateTime());
    }

    public static String print(DateTime dateTime) {
        return print(dateTime, ISODateTimeFormat.dateTime());
    }

    public static DateTime parse(String string, DateTimeFormatter formatter) {
        return formatter.parseDateTime(string);
    }

    public static String print(DateTime dateTime, DateTimeFormatter formatter) {
        return formatter.print(dateTime.getMillis());
    }

    public static DateTime parse(String string, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return parse(string, formatter);
    }

    public static String print(DateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return print(dateTime, formatter);
    }
}
