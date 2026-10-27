package org.example.reader;

import org.example.model.Order;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.math.BigDecimal;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TxtOrderReaderTest {

    @Test
    void read_AnalyzesAParceValidFile() throws IOException {
        List<String> lines = List.of("2021-02-09T16:00:22|Industrial|8800",
                "2021-02-09T08:42:59|Power Engineer|17480");

        TxtOrderReader reader = new TxtOrderReader();
        List<Order> orders = reader.read(lines);

        assertEquals(2, orders.size());
        assertEquals("Industrial", orders.get(0).getCompany());
        assertEquals(0, new BigDecimal("8800").compareTo(orders.get(0).getWeight()));
    }

    @Test
    void read_RaiseAnExceptionIfTheDataIsInvalid () throws IOException{
        List<String> lines = List.of("2021-02-09T16:00:22|Industrial|ABC");
        TxtOrderReader reader = new TxtOrderReader();

        assertThrows(NumberFormatException.class, () -> reader.read(lines));
    }
}
