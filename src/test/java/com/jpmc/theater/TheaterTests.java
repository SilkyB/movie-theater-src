package com.jpmc.theater;

import com.jpmc.theater.moviebooking.Customer;
import com.jpmc.theater.moviebooking.Reservation;
import com.jpmc.theater.moviebooking.Theater;
import com.jpmc.theater.util.LocalDateProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {

    /**
     * Total movie fees for multiple people
     */
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 37.5);

        Customer mat = new Customer("Mat Argon", "id-67890");
        reservation = theater.reserve(mat, 1, 2);
        assertEquals(reservation.totalFee(), 16);
    }


    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
