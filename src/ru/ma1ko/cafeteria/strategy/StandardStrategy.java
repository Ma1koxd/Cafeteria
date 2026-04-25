/*
 * Стандартная стратегия расчёта цены.
 * Используется как базовый способ вычисления стоимости.
 */
package ru.ma1ko.cafeteria.strategy;

import ru.ma1ko.cafeteria.composite.Node;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class StandardStrategy implements PriceStrategy {
    @Override
    public String name() {
        return "Стандартная";
    }

    @Override
    public BigDecimal total(Node node) {
        return node.cost().setScale(2, RoundingMode.HALF_UP);
    }
}
