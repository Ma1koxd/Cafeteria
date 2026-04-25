/*
 * Вспомогательный класс для безопасного чтения данных из консоли.
 * Содержит методы для ввода и базовой проверки значений.
 */
package ru.ma1ko.cafeteria.app;

import java.util.Objects;
import java.util.Scanner;

public final class Input {
    private final Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
    }

    public String line(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public int choice(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String raw = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(raw);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // fall through
            }
            System.out.printf("Введите число от %d до %d.%n", min, max);
        }
    }
}
