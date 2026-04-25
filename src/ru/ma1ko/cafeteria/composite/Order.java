/*
 * Корневой объект заказа в паттерне Composite.
 * Содержит позиции заказа и применяет стратегию расчёта цены.
 */
package ru.ma1ko.cafeteria.composite;

import ru.ma1ko.cafeteria.strategy.PriceStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public final class Order {
    private final Group root;
    private final PriceStrategy strategy;

    public Order(Group root, PriceStrategy strategy) {
        this.root = Objects.requireNonNull(root, "root");
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public BigDecimal total() {
        return strategy.total(root);
    }

    public int count() {
        return root.count();
    }

    public String text() {
        return root.text("");
    }

    public String strategyName() {
        return strategy.name();
    }
}
