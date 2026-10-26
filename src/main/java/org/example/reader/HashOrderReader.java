package org.example.reader;

import org.example.model.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HashOrderReader implements OrderSource{

    private Order parseLine (String line){
        String[] parts = line.split("#");
        LocalDateTime time = LocalDateTime.parse(parts[0]);
        String company = parts[1];
        BigDecimal weight = new BigDecimal(parts[2]);

        return new Order(time, company, weight);
    }

    @Override
    public List<Order> read (String filePath){
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
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            String firstLine = lines.filter(line -> !line.isBlank()).findFirst().orElse("");
            System.out.println("Hash reader видит: [" + firstLine + "]");
            return firstLine.contains("#");
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
