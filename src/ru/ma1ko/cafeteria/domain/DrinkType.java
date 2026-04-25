/*
 * Перечисление доступных типов напитков.
 * Хранит базовые параметры для приготовления и цены.
 */
package ru.ma1ko.cafeteria.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum DrinkType {
    CAPPUCCINO("Капучино", Area.COFFEE, new BigDecimal("45.00")),
    LATTE("Латте", Area.COFFEE, new BigDecimal("50.00")),
    AMERICANO("Американо", Area.COFFEE, new BigDecimal("40.00")),
    AMERICANO_WITH_MILK("Американо с молоком", Area.COFFEE, new BigDecimal("47.00")),
    ICE_LATTE("Айс Латте", Area.COFFEE, new BigDecimal("55.00")),
    CLASSIC_MILKSHAKE("Классический милкшейк", Area.MILKSHAKES, new BigDecimal("52.00")),
    COFFEE_MILKSHAKE("Кофейный милкшейк", Area.MILKSHAKES, new BigDecimal("58.00")),
    CHOCOLATE_MILKSHAKE("Шоколадный милкшейк", Area.MILKSHAKES, new BigDecimal("60.00")),
    MELON_RASPBERRY_LEMONADE("Дыня-Малина", Area.LEMONADE, new BigDecimal("48.00")),
    MANGO_PASSION_LEMONADE("Манго-Маракуйя", Area.LEMONADE, new BigDecimal("50.00")),
    PEACH_PROSECCO_LEMONADE("Персик-Просекко", Area.LEMONADE, new BigDecimal("54.00")),
    MOJITO_LEMONADE("Мохито", Area.LEMONADE, new BigDecimal("46.00")),
    LOOSE_TEA("Рассыпной чай", Area.TEA, new BigDecimal("35.00")),
    SEA_BUCKTHORN_TEA("Облепиховый чай", Area.TEA, new BigDecimal("42.00")),
    GINGER_TEA("Имбирный чай", Area.TEA, new BigDecimal("41.00")),
    RASPBERRY_TEA("Малиновый чай", Area.TEA, new BigDecimal("40.00")),
    CRANBERRY_TEA("Клюквенный чай", Area.TEA, new BigDecimal("40.00"));

    private final String displayName;
    private final Area area;
    private final BigDecimal cost;

    DrinkType(String displayName, Area area, BigDecimal cost) {
        this.displayName = displayName;
        this.area = area;
        this.cost = cost;
    }

    public String displayName() {
        return displayName;
    }

    public Area area() {
        return area;
    }

    public BigDecimal cost() {
        return cost;
    }

    public static List<DrinkType> byArea(Area area) {
        return Arrays.stream(values())
                .filter(type -> type.area == area)
                .toList();
    }
}
