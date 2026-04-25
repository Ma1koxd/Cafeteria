/*
 * Фабрика напитков для экономного производителя.
 * Создаёт более доступную базу напитков.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.DrinkType;

public final class EconomyFactory implements Factory {
    @Override
    public String producerName() {
        return "Средний (экономный)";
    }

    @Override
    public Drink create(DrinkType type) {
        return new BaseDrink(type.displayName(), producerName(), type.area(), type.cost());
    }
}
