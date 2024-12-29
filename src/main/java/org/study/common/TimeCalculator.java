package org.study.common;

import java.time.LocalDate;

public class TimeCalculator {

    private TimeCalculator() {}

    public static LocalDate getDateDaysAgo(int daysAgo) {
        return LocalDate.now().minusDays(daysAgo);
    }

}
