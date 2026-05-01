/*
 * Фабрика напитков для экономного производителя.
 * Создаёт более доступную базу напитков.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;

public final class EconomyFactory implements Factory {
    @Override
    public String producerName() {
        return "Средний (экономный)";
    }

    @Override
    public Drink create(String name, Area area, BigDecimal cost) {
        return new BaseDrink(name, producerName(), area, cost);
    }
}
