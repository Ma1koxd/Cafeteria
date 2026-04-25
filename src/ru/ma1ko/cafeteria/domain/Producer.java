/*
 * Перечисление вариантов производителя напитков.
 * Используется для выбора стратегии производства.
 */
package ru.ma1ko.cafeteria.domain;

public enum Producer {
    QUALITY("Качественный"),
    ECONOMY("Средний (экономный)");

    private final String displayName;

    Producer(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
