package org.example.reader;

import org.example.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TxtOrderReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void read_AnalyzesAParceValidFile() throws IOException {
        Path file = tempDir.resolve("test_orders.txt");
        String content = "2021-02-09T16:00:22|Industrial|8800\n" +
                "2021-02-09T08:42:59|Power Engineer|17480";
        Files.writeString(file, content);

        TxtOrderReader reader = new TxtOrderReader();
        List<Order> orders = reader.read(file.toString());

        assertEquals(2, orders.size());
        assertEquals("Industrial", orders.get(0).getCompany());
        assertEquals(0, new BigDecimal("8800").compareTo(orders.get(0).getWeight()));
    }

    @Test
    void read_RaiseAnExceptionIfTheDataIsInvalid () throws IOException{
        Path file = tempDir.resolve("bad_orders.txt");
        Files.writeString(file, "2021-02-09T16:00:22|Industrial|ABC");
        TxtOrderReader reader = new TxtOrderReader();

        assertThrows(NumberFormatException.class, () -> reader.read(file.toString()));
    }
}
