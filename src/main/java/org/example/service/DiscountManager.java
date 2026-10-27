package org.example.service;

public class DiscountManager {

    private int currentDiscount;
    private final int step;

    public DiscountManager (int currentDiscount, int step){
        this.currentDiscount = currentDiscount;
        this.step = step;
    }

    public int getCurrentDiscount() {
        return currentDiscount;
    }

    public void reduce(){
        currentDiscount = Math.max(0, currentDiscount -step);
    }
}
