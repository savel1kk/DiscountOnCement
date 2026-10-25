package org.example.model;

public class DiscountRules {

    private double pricePerKg;
    private  int startDiscount;
    private int discountStep;

    public DiscountRules (double pricePerKg, int startDiscount, int discountStep){
        this.pricePerKg = pricePerKg;
        this.startDiscount = startDiscount;
        this.discountStep = discountStep;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public int getDiscountStep() {
        return discountStep;
    }

    public int getStartDiscount() {
        return startDiscount;
    }
}
