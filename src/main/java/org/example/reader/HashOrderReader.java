package org.example.reader;

import org.example.model.Order;


import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class HashOrderReader implements OrderSource {

    private Order parseLine(String line) {
        String[] parts = line.split("#");
        LocalDateTime time = LocalDateTime.parse(parts[0]);
        String company = parts[1];
        BigDecimal weight = new BigDecimal(parts[2]);

        return new Order(time, company, weight);
    }

    @Override
    public boolean isSupported(String firstLine) {
        return firstLine.contains("#");

    }

    @Override
    public List<Order> read(List<String> lines) {
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) {
                orders.add(parseLine(line));
            }

        }
        return orders;

    }
}


