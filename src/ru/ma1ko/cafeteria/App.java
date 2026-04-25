/*
 * Главная точка входа приложения.
 * Запускает консольное меню и передаёт управление пользователю.
 */
package ru.ma1ko.cafeteria;

import ru.ma1ko.cafeteria.app.Menu;

import java.util.Scanner;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Menu(scanner).run();
        }
    }
}
