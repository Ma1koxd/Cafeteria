/*
 * Вспомогательный класс для безопасного чтения данных из консоли.
 * Содержит методы для ввода и базовой проверки значений.
 */
package ru.ma1ko.cafeteria.app;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Scope("singleton")
public class Input {
    private Scanner scanner;

    @PostConstruct
    public void init() {
        scanner = new Scanner(System.in);
    }

    @PreDestroy
    public void destroy() {
        if (scanner != null) {
            scanner.close();
        }
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
