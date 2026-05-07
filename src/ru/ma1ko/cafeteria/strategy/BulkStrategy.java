/*
 * Стратегия расчёта цены для крупных заказов.
 * Применяет скидку при превышении заданного лимита.
 */
package ru.ma1ko.cafeteria.strategy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.ma1ko.cafeteria.composite.Node;
import ru.ma1ko.cafeteria.util.Limit;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("bulkStrategy")
@Scope("singleton")
public class BulkStrategy implements PriceStrategy {
    private static final BigDecimal DISCOUNT = new BigDecimal("0.10");

    @Override
    public String name() {
        return "Оптовая (скидка 10% от 4 позиций)";
    }

    @Override
    public BigDecimal total(Node node) {
        BigDecimal total = node.cost();
        if (node.count() < Limit.BULK_DISCOUNT_FROM) {
            return total.setScale(2, RoundingMode.HALF_UP);
        }
        return total.multiply(BigDecimal.ONE.subtract(DISCOUNT)).setScale(2, RoundingMode.HALF_UP);
    }
}
