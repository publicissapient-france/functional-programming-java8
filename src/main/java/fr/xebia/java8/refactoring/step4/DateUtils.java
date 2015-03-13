package fr.xebia.java8.refactoring.step4;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Parse String date without times
     *
     * @param date format  dd/MM/yyyy
     * @return
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * parse String date with time
     *
     * @param date format  dd/MM/yyyy HH:mm:ss
     * @return
     */
    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public static int age(LocalDate birthday, LocalDate now) {
        return Period.between(birthday, now).getYears();
    }

    public static LocalDateTime dayDateWithTime(LocalDate dayDate, int hour, int minute, int second) {
        return LocalDateTime.of(dayDate, LocalTime.of(hour, minute, second));
    }

    public static LocalDateTime addDuration(LocalDateTime date, int minute) {
        return date.plusMinutes(minute);
    }

    public static boolean dayAreEquals(LocalDateTime firstDateWithTime, LocalDateTime secondDateWithTime) {
        return firstDateWithTime.toLocalDate().equals(secondDateWithTime.toLocalDate());
    }

    public static String convertToTimeZone(String dateWithTime, ZoneId timeZoneFrom, ZoneId timeZoneTo) {
        return LocalDateTime.parse(dateWithTime, DATE_TIME_FORMATTER)
                .atZone(timeZoneFrom)
                .withZoneSameInstant(timeZoneTo)
                .format(DATE_TIME_FORMATTER);
    }

}
