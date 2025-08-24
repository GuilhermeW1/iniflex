package org.example.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatters {
    public static String localDateToStringBrPattern (LocalDate date) {
        DateTimeFormatter dtf = brDateLocalDatePattern();
        return dtf.format(date);
    }

    public static DateTimeFormatter brDateLocalDatePattern () {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public static String brPrice (BigDecimal price) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        return formatter.format(price);
    }
}
