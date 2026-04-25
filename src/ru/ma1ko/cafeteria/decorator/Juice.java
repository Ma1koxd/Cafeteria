/*
 * Декоратор, добавляющий сок в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import java.math.BigDecimal;
import java.util.Objects;

public final class Juice extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("8.00");
    private final String flavor;

    public Juice(Drink drink, String flavor) {
        super(drink);
        this.flavor = Objects.requireNonNull(flavor, "flavor").trim();
    }

    @Override
    public BigDecimal cost() {
        return drink().cost().add(UNIT_COST);
    }

    @Override
    public String description() {
        return drink().description() + " + сок " + flavor;
    }
}
