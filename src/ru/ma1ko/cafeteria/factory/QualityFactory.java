/*
 * Фабрика напитков для качественного производителя.
 * Создаёт более дорогую и качественную базу напитков.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;

public final class QualityFactory implements Factory {
    @Override
    public String producerName() {
        return "Качественный";
    }

    @Override
    public Drink create(String name, Area area, BigDecimal cost) {
        return new BaseDrink(name, producerName(), area, cost.add(new BigDecimal("15.00")));
    }
}
