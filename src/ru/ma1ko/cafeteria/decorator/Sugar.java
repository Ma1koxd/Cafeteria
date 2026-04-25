/*
 * Декоратор, добавляющий сахар в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import java.math.BigDecimal;

public final class Sugar extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("2.00");
    private final int count;

    public Sugar(Drink drink) {
        this(drink, 1);
    }

    public Sugar(Drink drink, int count) {
        super(drink);
        this.count = Math.max(1, count);
    }

    @Override
    public BigDecimal cost() {
        return drink().cost().add(UNIT_COST.multiply(BigDecimal.valueOf(count)));
    }

    @Override
    public String description() {
        return drink().description() + " + сахар x" + count;
    }
}
