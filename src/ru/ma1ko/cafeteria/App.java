/*
 * Главная точка входа приложения.
 * Запускает консольное меню и передаёт управление пользователю.
 */
package ru.ma1ko.cafeteria;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ma1ko.cafeteria.app.Menu;
import ru.ma1ko.cafeteria.config.AppConfig;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            context.getBean(Menu.class).run();
        }
    }
}
