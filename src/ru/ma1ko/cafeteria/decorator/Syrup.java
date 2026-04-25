/*
 * Декоратор, добавляющий сироп в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import java.math.BigDecimal;
import java.util.Objects;

public final class Syrup extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("7.00");
    private final String flavor;
    private final int count;

    public Syrup(Drink drink, String flavor, int count) {
        super(drink);
        this.flavor = Objects.requireNonNull(flavor, "flavor").trim();
        this.count = Math.max(1, count);
    }

    @Override
    public BigDecimal cost() {
        return drink().cost().add(UNIT_COST.multiply(BigDecimal.valueOf(count)));
    }

    @Override
    public String description() {
        return drink().description() + " + сироп " + flavor + " x" + count;
    }
}
