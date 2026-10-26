package org.example.reader;

import org.example.model.Order;

import java.util.List;

public interface OrderSource {

    List<Order> read(List<String> lines);
    boolean isSupported(String firstLine);
}
