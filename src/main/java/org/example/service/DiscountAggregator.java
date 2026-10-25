package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountAggregator {

    private final DiscountCalculator calculator;

    public DiscountAggregator (DiscountCalculator calculator){
        this.calculator = calculator;
    }

    public Map<String, Double> aggregate (List<Order> orders, DiscountRules rules){
        Map<String, Double> resultMap = new HashMap<>();
        DiscountManager manager = new DiscountManager(rules.getStartDiscount(), rules.getDiscountStep());

        for (Order order : orders) {
            double cost = calculator.calculateCost(order, rules.getPricePerKg(), manager.getCurrentDiscount());

            resultMap.merge(order.getCompany(), cost, Double :: sum);

            manager.reduce();
        }

        return resultMap;
    }
}
