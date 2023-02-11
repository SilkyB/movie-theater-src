package com.jpmc.theater;

import com.jpmc.theater.util.LocalDateProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LocalDateProviderTests {
    /**
     * Compare current date with LocalDateProvider's date
     */
    @Test
    void makeSureCurrentTime() {
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }
}
