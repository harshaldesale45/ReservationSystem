package com.staah.reservation.context;

import com.staah.reservation.strategy.QueryStrategy;

import java.util.HashMap;
import java.util.Map;

public class QueryContext {
    private final Map<String, QueryStrategy> strategies = new HashMap<>();

    public void addStrategy(String command, QueryStrategy strategy) {
        strategies.put(command, strategy);
    }

    public void executeStrategy(String command, String hotelId, String... params) {
        QueryStrategy strategy = strategies.get(command);
        if (strategy != null) {
            strategy.execute(hotelId, params);
        } else {
            System.out.println("Invalid command.");
        }
    }
}
