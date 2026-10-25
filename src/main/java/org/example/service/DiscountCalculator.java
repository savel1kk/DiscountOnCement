package org.example.service;

import org.example.model.Order;

public class DiscountCalculator {

    public double calculateCost (Order order, double pricePerKg, int currentDiscount){
        return order.getWeight() * pricePerKg * (1.0 - currentDiscount / 100.0);
    }
}
