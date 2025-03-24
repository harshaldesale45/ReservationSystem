package com.staah.reservation.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class Booking {
    private String hotelId;
    private String arrival;
    private String departure;
    private String roomType;
    private String roomRate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public LocalDate getArrival() { return LocalDate.parse(arrival, FORMATTER); }
    public LocalDate getDeparture() { return LocalDate.parse(departure, FORMATTER); }
}
