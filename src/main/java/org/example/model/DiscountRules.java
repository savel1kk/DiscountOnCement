package org.example.model;

import java.math.BigDecimal;

public class DiscountRules {

    private BigDecimal pricePerKg;
    private int startDiscount;
    private int discountStep;

    public DiscountRules (BigDecimal pricePerKg, int startDiscount, int discountStep) {
        this.pricePerKg = pricePerKg;
        this.startDiscount = startDiscount;
        this.discountStep = discountStep;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public int getDiscountStep() {
        return discountStep;
    }

    public int getStartDiscount() {
        return startDiscount;
    }
}
