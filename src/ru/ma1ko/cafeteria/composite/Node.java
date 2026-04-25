/*
 * Базовый элемент структуры заказа в паттерне Composite.
 * Определяет общий контракт для группы и товара.
 */
package ru.ma1ko.cafeteria.composite;

import java.math.BigDecimal;

public interface Node {
    String name();

    BigDecimal cost();

    int count();

    String text(String prefix);
}
