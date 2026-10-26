package org.example;


import org.example.model.DiscountRules;
import org.example.model.FileTask;
import org.example.reader.HashOrderReader;
import org.example.reader.OrderSource;
import org.example.reader.TxtOrderReader;
import org.example.service.PromotionApp;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<OrderSource> readers = List.of(
                new TxtOrderReader(),
                new HashOrderReader()
        );
        List<FileTask> tasks = List.of(
                new FileTask("discount_day.txt", "result_txt.txt"),
                new FileTask("discount_day_without_ext", "result_hash.txt")
        );
        DiscountRules rules = new DiscountRules(new BigDecimal("10"), 50, 10);
        PromotionApp app = new PromotionApp(rules, readers, tasks);
        app.run();


    }
}