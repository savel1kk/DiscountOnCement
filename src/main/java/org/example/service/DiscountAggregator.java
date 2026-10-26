package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountAggregator {

    private final DiscountCalculator calculator;

    public DiscountAggregator (DiscountCalculator calculator){
        this.calculator = calculator;
    }

    public Map<String, BigDecimal> aggregate (List<Order> orders, DiscountRules rules){
        Map<String, BigDecimal> resultMap = new HashMap<>();
        DiscountManager manager = new DiscountManager(rules.getStartDiscount(), rules.getDiscountStep());

        for (Order order : orders) {
            BigDecimal cost = calculator.calculateCost(order, rules.getPricePerKg(), manager.getCurrentDiscount());

            resultMap.merge(order.getCompany(), cost, BigDecimal::add);

            manager.reduce();
        }

        return resultMap;
    }
}
