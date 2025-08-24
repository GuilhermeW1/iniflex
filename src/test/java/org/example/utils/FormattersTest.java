package org.example.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormattersTest {

    @Test
    void testFormatLocalDateToStringBrPattern () {
        LocalDate localDate = LocalDate.now();
        String date = Formatters.localDateToStringBrPattern(localDate);
        assertEquals(10, date.length());
        assertEquals(3, date.split("/").length);
    }

    @Test
    void testFormatDate() {
       String date = "01/01/2000";
       LocalDate localDate = LocalDate.parse(date, Formatters.brDateLocalDatePattern());
       assertEquals("2000-01-01", localDate.toString());
    }

    @Test
    void testBrPriceFormat() {
        List<BigDecimal> nums = List.of(
                new BigDecimal("1000.00"),
                new BigDecimal("12345.00"),
                new BigDecimal("10000000000.00")
        );

        for (BigDecimal num : nums) {
            String formated = Formatters.brPrice(num);
            String[] parts = formated.split(",");

            assertEquals(2, parts[1].length());

            String[] dots = parts[0].split("\\.");


            for (int i = 1; i < dots.length; i++) {
                assertEquals(3, dots[i].length());
            }

        }
    }
}
