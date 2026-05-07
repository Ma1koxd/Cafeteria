/*
 * Стандартная стратегия расчёта цены.
 * Используется как базовый способ вычисления стоимости.
 */
package ru.ma1ko.cafeteria.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.ma1ko.cafeteria.composite.Node;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("standardStrategy")
@Primary
@Scope("singleton")
public class StandardStrategy implements PriceStrategy {
    @Override
    public String name() {
        return "Стандартная";
    }

    @Override
    public BigDecimal total(Node node) {
        return node.cost().setScale(2, RoundingMode.HALF_UP);
    }
}
