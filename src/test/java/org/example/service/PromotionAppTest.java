package org.example.service;

import org.example.model.DiscountRules;
import org.example.model.FileTask;
import org.example.reader.HashOrderReader;
import org.example.reader.OrderSource;
import org.example.reader.TxtOrderReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PromotionAppTest {

    @TempDir
    Path tempDir;

    @Test
    void run_ProcessingAndGeneratingTheResult () throws IOException {
        Path inputTxt = tempDir.resolve("input.txt");
        Path outputTxt = tempDir.resolve("output.txt");

        Files.writeString(inputTxt, "2021-02-09T16:00:22|Company|100");
        DiscountRules rules = new DiscountRules(new BigDecimal("10.0"), 50, 5);
        List<OrderSource> readers = List.of(new TxtOrderReader(), new HashOrderReader());
        List<FileTask> tasks = List.of(new FileTask(inputTxt.toString(), outputTxt.toString()));
        PromotionApp app = new PromotionApp(rules, readers, tasks);

        app.run();

        Assertions.assertEquals(true, Files.exists(outputTxt));
        String resultContent = Files.readString(outputTxt).trim();
        Assertions.assertEquals("Company - 500.00", resultContent);
    }
}
