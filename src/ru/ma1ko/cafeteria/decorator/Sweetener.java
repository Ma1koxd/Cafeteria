/*
 * Декоратор, добавляющий подсластитель в напиток.
 */
package ru.ma1ko.cafeteria.decorator;

import static ru.ma1ko.cafeteria.util.DrinkValidation.requireCount;
import java.math.BigDecimal;

public final class Sweetener extends DrinkWrap {
    private static final BigDecimal UNIT_COST = new BigDecimal("3.00");
    private final int count;

    public Sweetener(Drink drink, int count) {
        super(drink);
        this.count = requireCount(count, "Количество сахарозаменителя");
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
