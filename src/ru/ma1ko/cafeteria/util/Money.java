/*
 * Вспомогательный класс для работы с денежными значениями.
 * Форматирует и нормализует суммы в проекте.
 */
package ru.ma1ko.cafeteria.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class Money {
    private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));

    static {
        FORMAT.setMinimumFractionDigits(2);
        FORMAT.setMaximumFractionDigits(2);
    }

    private Money() {
    }

    public static String format(BigDecimal value) {
        return FORMAT.format(value);
    }
}
