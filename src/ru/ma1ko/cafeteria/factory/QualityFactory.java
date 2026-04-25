/*
 * Фабрика напитков для качественного производителя.
 * Создаёт более дорогую и качественную базу напитков.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.DrinkType;

public final class QualityFactory implements Factory {
    @Override
    public String producerName() {
        return "Качественный";
    }

    @Override
    public Drink create(DrinkType type) {
        return new BaseDrink(type.displayName(), producerName(), type.area(), type.cost().add(new java.math.BigDecimal("15.00")));
    }
}
