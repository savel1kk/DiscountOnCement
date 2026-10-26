package org.example.model;

import java.math.BigDecimal;

public class DiscountRules {

    private final BigDecimal pricePerKg;
    private final int startDiscount;
    private final int discountStep;

    public DiscountRules (BigDecimal pricePerKg, int startDiscount, int discountStep) {
        if(startDiscount < 0 || startDiscount > 100){
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100");
        }
        if(discountStep < 0 || discountStep > 100){
            throw new IllegalArgumentException("Шаг скидки должен быть от 0 до 100");
        }
        this.discountStep = discountStep;
        this.startDiscount = startDiscount;
        this.pricePerKg = pricePerKg;
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
