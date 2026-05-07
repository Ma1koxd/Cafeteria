package ru.ma1ko.cafeteria.util;

public final class DrinkValidation {
    private DrinkValidation() {
    }

    public static int requireCount(int count, String fieldName) {
        if (count < 1 || count > Limit.MAX_SUGAR) {
            throw new IllegalArgumentException(
                    fieldName + " должно быть от 1 до " + Limit.MAX_SUGAR
            );
        }
        return count;
    }

    public static String requireNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }
}
