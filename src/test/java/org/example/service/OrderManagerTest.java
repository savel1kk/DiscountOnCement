package org.example.service;

import org.example.model.Order;
import org.example.reader.OrderSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {

    @Mock
    private OrderSource mockSource;

    @InjectMocks
    private OrderManager orderManager;

    @Test
    void getSortedOrders_shouldSortByTime() {
        Order lateOrder = new Order(LocalDateTime.of(2021, 2, 9, 16, 0), "Company1", 100);
        Order earlyOrder = new Order(LocalDateTime.of(2021, 2, 9, 8, 0), "Company2", 200);

        when(mockSource.read()).thenReturn(List.of(lateOrder, earlyOrder));

        List<Order> result = orderManager.getSortedOrders(mockSource);

        assertEquals(2, result.size());
        assertEquals("Company2", result.get(0).getCompany());
        assertEquals("Company1", result.get(1).getCompany());
    }
}
