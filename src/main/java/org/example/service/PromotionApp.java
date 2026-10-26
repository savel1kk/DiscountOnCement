package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.FileTask;
import org.example.model.Order;
import org.example.reader.OrderSource;
import org.example.reader.OrderSourceFactory;
import org.example.writer.FileResultWriter;
import org.example.writer.ResultWriter;

import java.math.BigDecimal;
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

    public void processFile(String inputFilePath, String outputFilePath){
        System.out.println("Обработка файла: " + inputFilePath + " ... ");
        OrderSource source = OrderSourceFactory.getSource(inputFilePath, readers);
        List<Order> orders = source.read(inputFilePath);
        List<Order> sortedOrders = orderManager.getSortedOrders(orders);
        Map<String, BigDecimal> results = aggregator.aggregate(sortedOrders, rules);
        ResultWriter writer = new FileResultWriter(outputFilePath);
        writer.write(results);
    }
}
