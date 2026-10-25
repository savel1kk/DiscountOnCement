package org.example.reader;

import org.example.model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TxtOrderReader implements OrderSource {

    private final String filePath;

    public TxtOrderReader(String filePath){
        this.filePath = filePath;
    }

    private Order parseLine (String line){
        String[] parts = line.split("\\|");
        LocalDateTime time = LocalDateTime.parse(parts[0]);
        String company = parts[1];
        double weight = Double.parseDouble(parts[2]);

        return new Order(time, company, weight);
    }

    @Override
    public List<Order> read (){
        List<Order> orders = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for(String line : lines){
                if (line.isBlank()){
                    continue;
                }

                Order order = parseLine(line);
                orders.add(order);
            }

            return orders;
        } catch (IOException e){
            throw new RuntimeException("Ошибка чтения", e);
        }
    }
    @Override
    public boolean isSupported(String filePath){
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            return !lines.isEmpty() && lines.get(0).contains("|");
        } catch (IOException e){
            return false;
        }
    }
}
