package org.example.model;

import java.time.LocalDateTime;

public class Order {

    private LocalDateTime time;
    private String company;
    private double weight;

    public Order (LocalDateTime time, String company, double weight){
        this.time = time;
        this.company = company;
        this.weight = weight;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getCompany() {
        return company;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return time + "|" + company + "|" + weight;

    }

}
