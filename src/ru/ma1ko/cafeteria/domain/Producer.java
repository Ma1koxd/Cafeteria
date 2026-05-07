package ru.ma1ko.cafeteria.domain;

import java.math.BigDecimal;

public enum Producer {
    QUALITY("Качественный", new BigDecimal("15.00")),
    ECONOMY("Средний (экономный)", BigDecimal.ZERO);

    private final String displayName;
    private final BigDecimal extraCost;

    Producer(String displayName, BigDecimal extraCost) {
        this.displayName = displayName;
        this.extraCost = extraCost;
    }

    public String displayName() {
        return displayName;
    }

    public BigDecimal priceFor(BigDecimal baseCost) {
        return baseCost.add(extraCost);
    }
}
