/*
 * Контракт стратегии выбора производителя напитков.
 * Определяет имя и корректировку цены для выбранного варианта.
 */
package ru.ma1ko.cafeteria.strategy;

import java.math.BigDecimal;

public interface ProducerStrategy {
    String name();

    String producerName();

    BigDecimal adjustCost(BigDecimal baseCost);
}
