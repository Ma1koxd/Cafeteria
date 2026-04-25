/*
 * Стратегия для среднего экономного производителя напитков.
 * Позволяет собирать более доступные по цене напитки.
 */
package ru.ma1ko.cafeteria.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class EconomyProducerStrategy implements ProducerStrategy {
    @Override
    public String name() {
        return "Средний (экономный)";
    }

    @Override
    public String producerName() {
        return "Средний (экономный)";
    }

    @Override
    public BigDecimal adjustCost(BigDecimal baseCost) {
        return baseCost.setScale(2, RoundingMode.HALF_UP);
    }
}
