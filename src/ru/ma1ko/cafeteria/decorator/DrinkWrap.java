/*
 * Абстрактный декоратор напитка.
 * Добавляет новые ингредиенты поверх базового напитка.
 */
package ru.ma1ko.cafeteria.decorator;

import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class DrinkWrap implements Drink {
    private final Drink drink;

    protected DrinkWrap(Drink drink) {
        this.drink = Objects.requireNonNull(drink, "drink");
    }

    @Override
    public String name() {
        return drink.name();
    }

    @Override
    public String producer() {
        return drink.producer();
    }

    @Override
    public Area area() {
        return drink.area();
    }

    @Override
    public BigDecimal cost() {
        return drink.cost();
    }

    @Override
    public String description() {
        return drink.description();
    }

    @Override
    public Drink addSugar() {
        return new Sugar(this);
    }

    protected Drink drink() {
        return drink;
    }
}
