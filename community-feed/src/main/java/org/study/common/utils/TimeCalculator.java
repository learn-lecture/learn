package org.study.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeCalculator() {}

    public static LocalDate getDateDaysAgo(int daysAgo) {
        return LocalDate.now().minusDays(daysAgo);
    }

    public static String getFormattedDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

}
