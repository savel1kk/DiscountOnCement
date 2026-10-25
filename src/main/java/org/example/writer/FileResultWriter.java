package org.example.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileResultWriter implements ResultWriter{

    private final String filePath;

    public FileResultWriter (String filePath){
        this.filePath = filePath;
    }

    @Override
    public void write(Map<String, Double> results){
        List<String> lines = new ArrayList<>();

        results.forEach((company, totalCost) -> {
            String line = company + " - " + String.format("%.2f", totalCost);
            lines.add(line);
        });

        try {
            Files.write(Path.of(filePath), lines);
            System.out.println("Результат успешно сохранен в файл: " + filePath);
        } catch (IOException e){
            throw new RuntimeException("Ошибка при записи в файл: " + filePath, e);
        }
    }
}
