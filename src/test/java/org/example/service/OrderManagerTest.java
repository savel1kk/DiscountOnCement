package org.example.service;

import org.example.model.Order;
import org.example.reader.OrderSource;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderManagerTest {


    @Test
    void getSortedOrders_shouldSortByTime() {
        Order lateOrder = new Order(LocalDateTime.of(2021, 2, 9, 16, 0), "Company1", new BigDecimal("100"));
        Order earlyOrder = new Order(LocalDateTime.of(2021, 2, 9, 8, 0), "Company2", new BigDecimal("200"));

        OrderManager orderManager = new OrderManager();
        List<Order> result = orderManager.getSortedOrders(List.of(lateOrder, earlyOrder));

        assertEquals(2, result.size());
        assertEquals("Company2", result.get(0).getCompany());
        assertEquals("Company1", result.get(1).getCompany());
    }
}
