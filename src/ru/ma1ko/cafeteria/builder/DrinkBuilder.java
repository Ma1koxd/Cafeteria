/*
 * Builder для пошаговой сборки напитка.
 * Накладывает выбранную основу и добавки.
 */
package ru.ma1ko.cafeteria.builder;

import ru.ma1ko.cafeteria.factory.Factory;
import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.decorator.Juice;
import ru.ma1ko.cafeteria.decorator.Sugar;
import ru.ma1ko.cafeteria.decorator.Sweetener;
import ru.ma1ko.cafeteria.decorator.Syrup;
import ru.ma1ko.cafeteria.domain.Area;
import ru.ma1ko.cafeteria.domain.DrinkType;

import java.math.BigDecimal;
import java.util.Objects;

public final class DrinkBuilder {
    private String name;
    private Factory factory;
    private Area area;
    private BigDecimal cost = BigDecimal.ZERO;
    private String details = "";

    private int sugarCount;
    private int sweetenerCount;
    private String syrupFlavor;
    private int syrupCount;
    private String juiceFlavor;

    public DrinkBuilder type(DrinkType type) {
        Objects.requireNonNull(type, "type");
        this.name = type.displayName();
        this.area = type.area();
        this.cost = type.cost();
        return this;
    }

    public DrinkBuilder custom(String name, Area area, BigDecimal cost) {
        this.name = Objects.requireNonNull(name, "name");
        this.area = Objects.requireNonNull(area, "area");
        this.cost = Objects.requireNonNull(cost, "cost");
        return this;
    }

    public DrinkBuilder factory(Factory factory) {
        this.factory = Objects.requireNonNull(factory, "factory");
        return this;
    }

    public DrinkBuilder sugars(int count) {
        this.sugarCount = Math.max(0, count);
        return this;
    }

    public DrinkBuilder sweetener(int count) {
        this.sweetenerCount = Math.max(0, count);
        return this;
    }

    public DrinkBuilder syrup(String flavor, int count) {
        this.syrupFlavor = flavor == null ? null : flavor.trim();
        this.syrupCount = Math.max(0, count);
        return this;
    }

    public DrinkBuilder juice(String flavor) {
        this.juiceFlavor = flavor == null ? null : flavor.trim();
        return this;
    }

    public Drink build() {
        if (name == null || area == null) {
            throw new IllegalStateException("Drink type is not set");
        }

        Drink drink = factory == null
                ? new BaseDrink(name, "", area, cost, details)
                : factory.create(name, area, cost);

        if (juiceFlavor != null && !juiceFlavor.isBlank()) {
            drink = new Juice(drink, juiceFlavor);
        }
        if (sugarCount > 0) {
            drink = new Sugar(drink, sugarCount);
        }
        if (sweetenerCount > 0) {
            drink = new Sweetener(drink, sweetenerCount);
        }
        if (syrupFlavor != null && !syrupFlavor.isBlank() && syrupCount > 0) {
            drink = new Syrup(drink, syrupFlavor, syrupCount);
        }

        clear();
        return drink;
    }

    private void clear() {
        name = null;
        area = null;
        cost = BigDecimal.ZERO;
        details = "";
        factory = null;
        sugarCount = 0;
        sweetenerCount = 0;
        syrupFlavor = null;
        syrupCount = 0;
        juiceFlavor = null;
    }
}
