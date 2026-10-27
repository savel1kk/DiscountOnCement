package org.example.reader;



import java.util.List;

public class OrderSourceFactory {

    public static OrderSource getSource(String firstLine, List<OrderSource> readers){
        return readers.stream()
                .filter(reader -> reader.isSupported(firstLine))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Неизвестный формат файла"));
    }
}
