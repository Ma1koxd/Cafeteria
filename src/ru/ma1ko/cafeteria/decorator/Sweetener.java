/*
 * Декоратор, добавляющий подсластитель в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import java.math.BigDecimal;

public final class Sweetener extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("3.00");
    private final int count;

    public Sweetener(Drink drink) {
        this(drink, 1);
    }

    public Sweetener(Drink drink, int count) {
        super(drink);
        this.count = Math.max(1, count);
    }

    @Override
    public BigDecimal cost() {
        return drink().cost().add(UNIT_COST.multiply(BigDecimal.valueOf(count)));
    }

    @Override
    public String description() {
        return drink().description() + " + сахарозаменитель x" + count;
    }
}
