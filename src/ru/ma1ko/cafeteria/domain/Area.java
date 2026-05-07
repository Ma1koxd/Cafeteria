/*
 * Перечисление зон меню кафе.
 * Разделяет напитки по категориям.
 */
package ru.ma1ko.cafeteria.domain;

public enum Area {
    COFFEE("Кофе"),
    MILKSHAKES("Милкшейки"),
    LEMONADE("Лимонады"),
    MATCHA("Матча"),
    TEA("Чай");

    private final String displayName;

    Area(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
