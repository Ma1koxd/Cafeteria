/*
 * Декоратор, добавляющий сок в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import java.math.BigDecimal;
import static ru.ma1ko.cafeteria.util.DrinkValidation.requireNotBlank;

public final class Juice extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("8.00");
    private final String flavor;

    public Juice(Drink drink, String flavor) {
        super(drink);
        this.flavor = requireNotBlank(flavor, "Juice flavor is not set");
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
