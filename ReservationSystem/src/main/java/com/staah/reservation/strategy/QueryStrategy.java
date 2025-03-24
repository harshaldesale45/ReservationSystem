package com.staah.reservation.strategy;

import com.staah.reservation.model.Booking;
import com.staah.reservation.model.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface QueryStrategy {
    void execute(String hotelId, String... params);

    default long getTotalRooms(Hotel hotel, String roomType) {
        return hotel.getRooms().stream()
                .filter(r -> r.getRoomType().equals(roomType))
                .count();
    }

    default long getBookedRooms(List<Booking> bookings , String hotelId, String roomType, LocalDate startDate, LocalDate endDate) {
        return bookings.stream()
                .filter(b -> b.getHotelId().equals(hotelId) && b.getRoomType().equals(roomType))
                .filter(b -> !(b.getDeparture().isBefore(startDate) || b.getArrival().isAfter(endDate)))
                .count();
    }
}
