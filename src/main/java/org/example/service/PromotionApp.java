package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.Order;
import org.example.reader.OrderSource;
import org.example.reader.OrderSourceFactory;
import org.example.writer.FileResultWriter;
import org.example.writer.ResultWriter;

import java.util.List;
import java.util.Map;

public class PromotionApp {

    private final DiscountRules rules;
    private final OrderManager orderManager;
    private final DiscountAggregator aggregator;

    public PromotionApp() {
        this.rules = new DiscountRules(10.0, 50, 5);
        this.orderManager = new OrderManager();
        this.aggregator = new DiscountAggregator(new DiscountCalculator());
    }

    public void run() {
        processFile("discount_day.txt", "result_txt.txt");
        processFile("discount_day_without_ext", "result_hash.txt");

        System.out.println("Все файлы успешно обработаны!");
    }

    public void processFile(String inputFilePath, String outputFilePath){
        System.out.println("Обработка файла: " + inputFilePath + " ... ");

        OrderSource source = OrderSourceFactory.getSource(inputFilePath);
        List<Order> sortedOrders = orderManager.getSortedOrders(source);
        Map<String, Double> results = aggregator.aggregate(sortedOrders, rules);
        ResultWriter writer = new FileResultWriter(outputFilePath);
        writer.write(results);
    }
}
