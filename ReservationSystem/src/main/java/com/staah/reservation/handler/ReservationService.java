package com.staah.reservation.handler;

import com.staah.reservation.context.QueryContext;
import com.staah.reservation.model.Booking;
import com.staah.reservation.model.Hotel;
import com.staah.reservation.strategy.AvailabilityStrategy;
import com.staah.reservation.strategy.SearchStrategy;
import com.staah.reservation.util.FileUtil;


import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private final List<Hotel> hotels;
    private final List<Booking> bookings;
    private final QueryContext context = new QueryContext();

    public ReservationService(String hotelsFile, String bookingsFile) {
        this.hotels = FileUtil.readJsonFile(hotelsFile, Hotel[].class);
        this.bookings = FileUtil.readJsonFile(bookingsFile, Booking[].class);
        setupStrategies();
    }

    private void setupStrategies() {
        context.addStrategy("Availability", new AvailabilityStrategy(hotels, bookings));
        context.addStrategy("Search", new SearchStrategy(hotels, bookings));
    }


    public void startCommandLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();
            if (command.isBlank()) break;

            processCommand(command);
        }

        scanner.close();
    }

    public void processCommand(String input) {
        String[] parts = input.split("[(), ]+");
        if (parts.length < 3) {
            System.out.println("Invalid command format.");
            return;
        }

        String command = parts[0];
        String hotelId = parts[1];
        String param1 = parts[2];
        String param2 = parts.length > 3 ? parts[3] : "";

        context.executeStrategy(command, hotelId, param1, param2);
    }
}

