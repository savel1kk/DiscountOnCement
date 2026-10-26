package org.example.reader;



import java.util.List;

public class OrderSourceFactory {

    public static OrderSource getSource(String filePath, List<OrderSource> readers){
        return readers.stream()
                .filter(reader -> reader.isSupported(filePath))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Неизвестный формат файла" + filePath));
    }
}
