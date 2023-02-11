package com.jpmc.theater.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HumanRedableFormat {

    /**
     * Convert time to Human Readable format
     *
     * @param duration
     * @return
     */
    public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    /**
     * Adds (s) postfix to handle plural correctly
     * @param value
     * @return
     */
    private static String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

}
