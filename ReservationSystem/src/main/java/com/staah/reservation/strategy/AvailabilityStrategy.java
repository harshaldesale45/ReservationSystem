package com.staah.reservation.strategy;


import com.staah.reservation.model.Booking;
import com.staah.reservation.model.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AvailabilityStrategy implements QueryStrategy {
    private final List<Hotel> hotels;
    private final List<Booking> bookings;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public AvailabilityStrategy(List<Hotel> hotels, List<Booking> bookings) {
        this.hotels = hotels;
        this.bookings = bookings;
    }

    @Override
    public void execute(String hotelId, String... params) {
        String dateRange = params[0];
        String roomType = params[1];

        LocalDate startDate;
        LocalDate endDate;

        if (dateRange.contains("-")) {
            String[] dates = dateRange.split("-");
            startDate = LocalDate.parse(dates[0], DATE_FORMAT);
            endDate = LocalDate.parse(dates[1], DATE_FORMAT);
        } else {
            startDate = LocalDate.parse(dateRange, DATE_FORMAT);
            endDate = startDate;
        }

        Hotel hotel = hotels.stream()
                .filter(h -> h.getId().equals(hotelId))
                .findFirst()
                .orElse(null);

        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        long totalRooms = getTotalRooms(hotel, roomType);
        long bookedRooms = getBookedRooms(bookings , hotelId , roomType , startDate , endDate);
        

        System.out.println("Availability: " + (totalRooms - bookedRooms));
    }
    
}
