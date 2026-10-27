package org.example.writer;

import java.math.BigDecimal;
import java.util.Map;

public interface ResultWriter {

    void write (Map<String, BigDecimal> results);

}
