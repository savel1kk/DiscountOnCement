package org.example.reader;

import org.example.model.Order;

import java.util.List;

public interface OrderSource {

    List<Order> read();
    boolean isSupported(String filePath);
}
