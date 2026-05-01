/*
 * Общий контракт фабрики напитков.
 * Создаёт основу напитка для выбранного типа.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;

public interface Factory {
    String producerName();

    Drink create(String name, Area area, BigDecimal cost);
}
