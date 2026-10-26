package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private LocalDateTime time;
    private String company;
    private BigDecimal weight;

    public Order (LocalDateTime time, String company, BigDecimal weight){
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

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return time + "|" + company + "|" + weight;

    }

}
