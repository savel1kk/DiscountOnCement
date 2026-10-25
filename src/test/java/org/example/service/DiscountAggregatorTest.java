package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountAggregatorTest {

    @Test
    void aggregate_shouldCalculateDiscountCorrectly(){
        DiscountRules rules = new DiscountRules(10.0, 50, 10);
        DiscountCalculator calculator = new DiscountCalculator();
        DiscountAggregator aggregator = new DiscountAggregator(calculator);

        Order order1 = new Order(LocalDateTime.of(2021, 2, 9, 10, 0), "CementCorp", 100);
        Order order2 = new Order(LocalDateTime.of(2021, 2, 9, 11, 0),"CementCorp", 100);

        List<Order> orders = List.of(order1, order2);

        Map<String, Double> result = aggregator.aggregate(orders, rules);

        assertEquals(1, result.size());
        assertEquals(1100.0, result.get("CementCorp"), 0.001);
    }

    @Test
    void reduce_shouldNotGoBelowZero(){
        DiscountManager manager = new DiscountManager(5, 10);
        manager.reduce();
        assertEquals(0,manager.getCurrentDiscount());
    }
}
