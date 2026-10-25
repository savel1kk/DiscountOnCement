package org.example.service;

import org.example.model.Order;
import org.example.reader.OrderSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderManager {

    public List<Order> getSortedOrders (OrderSource source){
        List<Order> orders = new ArrayList<>(source.read());
        orders.sort(Comparator.comparing(Order::getTime));

        return orders;
    }
}
