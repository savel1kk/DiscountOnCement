package org.example.service;

import org.example.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountCalculator {

    public BigDecimal calculateCost (Order order, BigDecimal pricePerKg, int currentDiscount) {
        BigDecimal discountSum = BigDecimal.valueOf(currentDiscount);
        BigDecimal discountMultiplier = discountSum.divide(BigDecimal.valueOf(100));

        BigDecimal finalMultiplier = BigDecimal.ONE.subtract(discountMultiplier);
        BigDecimal result = order.getWeight()
                .multiply(pricePerKg)
                .multiply(finalMultiplier);
        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
