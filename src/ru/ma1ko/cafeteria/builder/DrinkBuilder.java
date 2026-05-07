/*
 * Builder для пошаговой сборки напитка.
 * Накладывает выбранную основу и добавки.
 */
package ru.ma1ko.cafeteria.builder;

import ru.ma1ko.cafeteria.decorator.BaseDrink;
import ru.ma1ko.cafeteria.domain.Producer;
import static ru.ma1ko.cafeteria.util.DrinkValidation.requireCount;
import static ru.ma1ko.cafeteria.util.DrinkValidation.requireNotBlank;
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
    private Producer producer = Producer.ECONOMY;
    private Area area;
    private BigDecimal cost = BigDecimal.ZERO;

    private int sugarCount;
    private int sweetenerCount;
    private String syrupFlavor;
    private int syrupCount;
    private String juiceFlavor;

    public void type(DrinkType type) {
        Objects.requireNonNull(type, "type");
        this.name = type.displayName();
        this.area = type.area();
        this.cost = type.cost();
    }

    public void custom(String name, Area area, BigDecimal cost) {
        this.name = Objects.requireNonNull(name, "name");
        this.area = Objects.requireNonNull(area, "area");
        this.cost = Objects.requireNonNull(cost, "cost");
    }

    public void producer(Producer producer) {
        this.producer = Objects.requireNonNull(producer, "Producer is not set");
    }

    public void sugars(int count) {
        this.sugarCount = requireCount(count, "Количество сахара");
    }

    public void sweetener(int count) {
        this.sweetenerCount = requireCount(count, "Количество сахарозаменителя");
    }

    public void syrup(String flavor, int count) {
        this.syrupFlavor = requireNotBlank(flavor, "Syrup flavor is not set");
        this.syrupCount = requireCount(count, "Количество сиропа");
    }

    public void juice(String flavor) {
        this.juiceFlavor = requireNotBlank(flavor, "Juice flavor is not set");
    }

    public Drink build() {
        if (name == null || area == null) {
            throw new IllegalStateException("Drink type is not set");
        }

        Drink drink = new BaseDrink(name, producer.displayName(), area, producer.priceFor(cost));

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
        producer = Producer.ECONOMY;
        sugarCount = 0;
        sweetenerCount = 0;
        syrupFlavor = null;
        syrupCount = 0;
        juiceFlavor = null;
    }
}
