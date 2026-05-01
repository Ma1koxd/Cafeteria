/*
 * Базовый напиток без добавок.
 * Используется как основа для декораторов и выбора производителя.
 */
package ru.ma1ko.cafeteria.decorator;

import ru.ma1ko.cafeteria.domain.Area;

import java.math.BigDecimal;
import java.util.Objects;

public class BaseDrink implements Drink {
    private final String name;
    private final String producer;
    private final Area area;
    private final BigDecimal cost;
    private final String details;

    public BaseDrink(String name, String producer, Area area, BigDecimal cost) {
        this(name, producer, area, cost, "");
    }

    public BaseDrink(String name, String producer, Area area, BigDecimal cost, String details) {
        this.name = Objects.requireNonNull(name, "name");
        this.producer = producer == null ? "" : producer.trim();
        this.area = Objects.requireNonNull(area, "area");
        this.cost = Objects.requireNonNull(cost, "cost");
        this.details = details == null ? "" : details.trim();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String producer() {
        return producer;
    }

    @Override
    public Area area() {
        return area;
    }

    @Override
    public BigDecimal cost() {
        return cost;
    }

    @Override
    public String description() {
        if (details.isBlank()) {
            return name;
        }
        return name + " — " + details;
    }

}
