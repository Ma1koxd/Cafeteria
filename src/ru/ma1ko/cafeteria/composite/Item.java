/*
 * Лист структуры заказа в паттерне Composite.
 * Представляет конкретный напиток с итоговой стоимостью.
 */
package ru.ma1ko.cafeteria.composite;

import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.util.Money;

import java.math.BigDecimal;
import java.util.Objects;

public final class Item implements Node {
    private final Drink drink;

    public Item(Drink drink) {
        this.drink = Objects.requireNonNull(drink, "drink");
    }

    @Override
    public String name() {
        return drink.name();
    }

    @Override
    public BigDecimal cost() {
        return drink.cost();
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public String text(String prefix) {
        String producer = drink.producer().isBlank() ? "" : " | Производитель: " + drink.producer();
        return prefix + "- " + drink.description() + producer + " (" + Money.format(cost()) + ")";
    }
}
