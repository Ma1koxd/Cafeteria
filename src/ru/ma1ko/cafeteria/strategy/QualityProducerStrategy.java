/*
 * Стратегия для качественного производителя напитков.
 * Даёт более дорогую, но более высокую по классу основу.
 */
package ru.ma1ko.cafeteria.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class QualityProducerStrategy implements ProducerStrategy {
    private static final BigDecimal SURCHARGE = new BigDecimal("15.00");

    @Override
    public String name() {
        return "Качественный";
    }

    @Override
    public String producerName() {
        return "Качественный";
    }

    @Override
    public BigDecimal adjustCost(BigDecimal baseCost) {
        return baseCost.add(SURCHARGE).setScale(2, RoundingMode.HALF_UP);
    }
}
