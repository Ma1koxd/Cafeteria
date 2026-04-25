/*
 * Снимок состояния заказа для уведомления наблюдателей.
 * Передаёт им актуальные данные без прямой зависимости от заказа.
 */
package ru.ma1ko.cafeteria.observer;

import java.math.BigDecimal;
import java.util.Objects;

public final class Snapshot {
    private final String event;
    private final String text;
    private final BigDecimal total;
    private final int count;
    private final String strategy;

    public Snapshot(String event, String text, BigDecimal total, int count, String strategy) {
        this.event = Objects.requireNonNull(event, "event");
        this.text = Objects.requireNonNull(text, "text");
        this.total = Objects.requireNonNull(total, "total");
        this.count = count;
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public String event() {
        return event;
    }

    public String text() {
        return text;
    }

    public BigDecimal total() {
        return total;
    }

    public int count() {
        return count;
    }

    public String strategy() {
        return strategy;
    }
}
