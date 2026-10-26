package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.FileTask;
import org.example.model.Order;
import org.example.reader.OrderSource;
import org.example.reader.OrderSourceFactory;
import org.example.writer.FileResultWriter;
import org.example.writer.ResultWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PromotionApp {

    private final DiscountRules rules;
    private final OrderManager orderManager;
    private final DiscountAggregator aggregator;
    private final List<OrderSource> readers;
    private final List<FileTask> tasks;

    public PromotionApp(DiscountRules rules, List<OrderSource> readers, List<FileTask> tasks) {
        this.rules = rules;
        this.orderManager = new OrderManager();
        this.aggregator = new DiscountAggregator(new DiscountCalculator());
        this.readers = readers;
        this.tasks = tasks;
    }

    public void run() {
        for(FileTask task : tasks) {
            processFile(task.inputPath(), task.outputPath());
        }
        System.out.println("Все файлы успешно обработаны!");
    }

    private void processFile(String inputFilePath, String outputFilePath){
       try {
           List<String> allLines = Files.readAllLines(Path.of(inputFilePath));
           String firstLine = allLines.stream()
                   .filter(line -> !line.isBlank())
                   .findFirst()
                   .orElse("");
           OrderSource source = OrderSourceFactory.getSource(firstLine, readers);
           List<Order> orders = source.read(allLines);
           Map<String, BigDecimal> results = aggregator.aggregate(orders, rules);
           ResultWriter writer = new FileResultWriter(outputFilePath);
           writer.write(results);
       } catch (IOException e){
           throw new RuntimeException("Ошибка чтения", e);
       }
    }
}
