package com.staah.reservation.strategy;


import com.staah.reservation.model.Booking;
import com.staah.reservation.model.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class SearchStrategy implements QueryStrategy {
    private final List<Hotel> hotels;
    private final List<Booking> bookings;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public SearchStrategy(List<Hotel> hotels, List<Booking> bookings) {
        this.hotels = hotels;
        this.bookings = bookings;
    }

    @Override
    public void execute(String hotelId, String... params) {
        int daysAhead = Integer.parseInt(params[0]);
        String roomType = params[1];

        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(daysAhead);

        Optional<Hotel> hotelOpt = hotels.stream()
                .filter(h -> h.getId().equals(hotelId))
                .findFirst();

        if (hotelOpt.isEmpty()) {
            System.out.println("Hotel not found.");
            return;
        }

        Hotel hotel = hotelOpt.get();
        String availabilityResult = findAvailableDateRanges(hotel, roomType, today, futureDate);
        System.out.println(availabilityResult);
    }

    private String findAvailableDateRanges(Hotel hotel, String roomType, LocalDate startDate, LocalDate endDate) {
        StringBuilder availableRanges = new StringBuilder();
        LocalDate rangeStart = null;
        int lastAvailable = -1;

        for (LocalDate current = startDate; !current.isAfter(endDate); current = current.plusDays(1)) {
            int availableRooms = calculateAvailability(hotel, roomType, current);

            if (availableRooms > 0) {
                if (rangeStart == null) {
                    rangeStart = current;
                }
                lastAvailable = availableRooms;
            } else {
                if (rangeStart != null) {
                    availabilityAppender(availableRanges, rangeStart, current.minusDays(1), lastAvailable);
                    rangeStart = null;
                }
            }
        }

        if (rangeStart != null) {
            availabilityAppender(availableRanges, rangeStart, endDate, lastAvailable);
        }

        return availableRanges.toString();
    }

    private int calculateAvailability(Hotel hotel, String roomType, LocalDate date) {
        long totalRooms =  getTotalRooms(hotel, roomType);
        long bookedRooms = getBookedRooms(bookings , hotel.getId() , roomType , date , date);

        return (int) (totalRooms - bookedRooms);
    }

    private void availabilityAppender(StringBuilder builder, LocalDate start, LocalDate end, int availableRooms) {
        if (builder.length() > 0) {
            builder.append(", ");
        }
        builder.append("(")
                .append(start.format(DATE_FORMAT))
                .append("-")
                .append(end.format(DATE_FORMAT))
                .append(", ")
                .append(availableRooms)
                .append(")");
    }
}

