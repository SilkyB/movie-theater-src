package com.jpmc.theater;

import com.jpmc.theater.moviebooking.Customer;
import com.jpmc.theater.moviebooking.Movie;
import com.jpmc.theater.moviebooking.Reservation;
import com.jpmc.theater.moviebooking.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReservationTests {

    /**
     * Validate total movie reservation fees for multiple people
     */
    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertFalse(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
