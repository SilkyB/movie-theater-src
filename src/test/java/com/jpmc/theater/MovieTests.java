package com.jpmc.theater;

import com.jpmc.theater.moviebooking.Movie;
import com.jpmc.theater.moviebooking.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

    /**
     * Test movie with Special discount
     */
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    /**
     * Test movie with Sequence discount
     */
    @Test
    void movieWithSequenceDiscount() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);

        // $3 discount for the movie showing 1st of the day
        Showing showing = new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(8, turningRed.calculateTicketPrice(showing));

        // $2 discount for the movie showing 2nd of the day
        showing = new Showing(turningRed, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(9, turningRed.calculateTicketPrice(showing));

        // $1 discount for the movie showing 7th of the day
        showing = new Showing(turningRed, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(10, turningRed.calculateTicketPrice(showing));

        // No discount sequence
        showing = new Showing(turningRed, 10, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(11, turningRed.calculateTicketPrice(showing));
    }

    /**
     * Test movie with Start Time discount
     */
    @Test
    void movieWithStartTime25PercentDiscount() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 10, 0);

        // No discount for time before 11 AM
        Showing showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 10)));
        assertEquals(10, theBatMan.calculateTicketPrice(showing));

        // No discount for time after 4 PM
        showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)));
        assertEquals(10, theBatMan.calculateTicketPrice(showing));

        // Discount for time at 11 AM (Edge condition)
        showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 00)));
        assertEquals(7.5, theBatMan.calculateTicketPrice(showing));

        // Discount for time at 4PM (Edge condition)
        showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 00)));
        assertEquals(7.5, theBatMan.calculateTicketPrice(showing));

        // Discount for time between 11 AM to 4 PM
        showing = new Showing(theBatMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 10)));
        assertEquals(7.5, theBatMan.calculateTicketPrice(showing));
    }

    /**
     * Test movie with maximum discount
     */
    @Test
    void movieWithMaximumDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);

        // Max Discount = startTimeDiscount (startTimeDiscount 3.125 sequenceDiscount 3.0 specialDiscount 2.5 )
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 20)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));

        // Max Discount = sequenceDiscount ( startTimeDiscount 0.0 sequenceDiscount 3.0 specialDiscount 2.5 )
        showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50)));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));

        // Max Discount = specialDiscount ( startTimeDiscount 0.0 sequenceDiscount 1.0 specialDiscount 2.5 )
        showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 35)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }
}
