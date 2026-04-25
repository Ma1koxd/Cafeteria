/*
 * Контракт стратегии расчёта цены в паттерне Strategy.
 * Позволяет менять способ вычисления стоимости заказа.
 */
package ru.ma1ko.cafeteria.strategy;

import ru.ma1ko.cafeteria.composite.Node;

import java.math.BigDecimal;

public interface PriceStrategy {
    String name();

    BigDecimal total(Node node);
}
