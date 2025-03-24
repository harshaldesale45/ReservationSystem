package com.staah.reservation;
import com.staah.reservation.handler.ReservationService;


public class Main {
    public static void main(String[] args) {
        if (args.length < 4 || !args[0].equals("--hotels") || !args[2].equals("--bookings")) {
        System.out.println("Usage: myapp --hotels <hotels.json> --bookings <bookings.json>");
        return;
    }

        String hotelsFile = args[1];
        String bookingsFile = args[3];

        ReservationService manager = new ReservationService(hotelsFile, bookingsFile);
        manager.startCommandLoop();}
    }