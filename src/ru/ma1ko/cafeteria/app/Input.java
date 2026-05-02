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

    public int choice(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String raw = scanner.nextLine().trim();
            if (raw.isEmpty()) {
                System.out.println("Пустой ввод. Попробуйте ещё раз.");
                continue;
            }
            try {
                int value = Integer.parseInt(raw);
                if (value < min || value > max) {
                    System.out.printf("Число вне диапазона (%d–%d).%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }
}
