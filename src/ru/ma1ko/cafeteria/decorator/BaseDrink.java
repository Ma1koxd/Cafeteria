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

    public BaseDrink(String name, String producer, Area area, BigDecimal cost) {
        this.name = Objects.requireNonNull(name, "Name is not set");
        this.producer = producer == null ? "" : producer.trim();
        this.area = Objects.requireNonNull(area, "Area is not set");
        this.cost = Objects.requireNonNull(cost, "Cost is not set");
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
        return name;
    }

}
