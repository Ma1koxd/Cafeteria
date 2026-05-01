/*
 * Общий контракт напитка в паттерне Decorator.
 * Описывает название, стоимость и состав напитка.
 */
package ru.ma1ko.cafeteria.decorator;

import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;

public interface Drink {
    String name();

    String producer();

    Area area();

    BigDecimal cost();

    String description();
}
