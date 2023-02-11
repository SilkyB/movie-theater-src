package com.jpmc.theater;

import com.jpmc.theater.moviebooking.Theater;
import com.jpmc.theater.util.LocalDateProvider;

public class Main {
    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
