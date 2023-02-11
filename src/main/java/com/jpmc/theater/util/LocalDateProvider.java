package com.jpmc.theater.util;

import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return Returns singleton instance of LocalDateProvider
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }

    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
