/*
 * Консольное меню кафе.
 * Собирает заказ, применяет паттерны и показывает результат пользователю.
 */
package ru.ma1ko.cafeteria.app;

import ru.ma1ko.cafeteria.builder.DrinkBuilder;
import ru.ma1ko.cafeteria.builder.OrderBuilder;
import ru.ma1ko.cafeteria.domain.Area;
import ru.ma1ko.cafeteria.domain.DrinkType;
import ru.ma1ko.cafeteria.observer.CostObs;
import ru.ma1ko.cafeteria.observer.DetailObs;
import ru.ma1ko.cafeteria.strategy.BulkStrategy;
import ru.ma1ko.cafeteria.strategy.EconomyProducerStrategy;
import ru.ma1ko.cafeteria.strategy.PriceStrategy;
import ru.ma1ko.cafeteria.strategy.ProducerStrategy;
import ru.ma1ko.cafeteria.strategy.QualityProducerStrategy;
import ru.ma1ko.cafeteria.strategy.StandardStrategy;
import ru.ma1ko.cafeteria.util.Limit;
import ru.ma1ko.cafeteria.util.Money;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class Menu {
    private static final int EXIT = 0;
    private static final int ADD = 1;
    private static final int CHANGE_PRICE = 2;
    private static final int SHOW = 3;
    private static final int FINISH = 4;

    private static final List<String> SYRUP_OPTIONS = List.of(
            "Карамель",
            "Солёная карамель",
            "Вишня",
            "Банан",
            "Лесной орех",
            "Шоколад"
    );

    private static final List<String> JUICE_OPTIONS = List.of(
            "Апельсиновый",
            "Ананасовый",
            "Вишнёвый",
            "Гранатовый"
    );

    private final Input input;
    private final OrderBuilder orderBuilder;
    private final DrinkBuilder drinkBuilder;

    public Menu(Scanner scanner) {
        this.input = new Input(Objects.requireNonNull(scanner, "scanner"));
        this.orderBuilder = new OrderBuilder();
        this.drinkBuilder = new DrinkBuilder();
        this.orderBuilder.addObserver(new DetailObs());
        this.orderBuilder.addObserver(new CostObs());
    }

    public void run() {
        System.out.println("Добро пожаловать в Cafeteria");
        boolean running = true;
        while (running) {
            printMain();
            int choice = input.choice("Введите пункт меню:", EXIT, FINISH);
            switch (choice) {
                case ADD -> addDrink();
                case CHANGE_PRICE -> orderBuilder.setStrategy(selectPriceStrategy());
                case SHOW -> showCurrent();
                case FINISH -> finish();
                case EXIT -> running = false;
                default -> {
                }
            }
        }
    }

    private void addDrink() {
        int section = input.choice(
                """
                        Выберите раздел:
                        1. Кофе
                        2. Милкшейки
                        3. Лимонады
                        4. Матча
                        5. Чай
                        6. Сиропы
                        0. Назад""",
                EXIT, 6
        );

        switch (section) {
            case 1 -> addCoffee();
            case 2 -> addMilkshake();
            case 3 -> addLemonade();
            case 4 -> addMatcha();
            case 5 -> addTea();
            case 6 -> addStandaloneSyrup();
            default -> {
            }
        }
    }

    private void addCoffee() {
        int sub = input.choice(
                """
                        Кофе:
                        1. Горячий
                        2. Холодный
                        0. Назад""",
                EXIT, 2
        );
        switch (sub) {
            case 1 -> addFixedDrink(
                    """
                            Горячий кофе:
                            1. Капучино
                            2. Латте
                            3. Американо
                            4. Американо с молоком
                            0. Назад""",
                    List.of(
                            DrinkType.CAPPUCCINO,
                            DrinkType.LATTE,
                            DrinkType.AMERICANO,
                            DrinkType.AMERICANO_WITH_MILK
                    ),
                    true
            );
            case 2 -> {
                int cold = input.choice("""
                        Холодный кофе:
                        1. Айс Латте
                        2. Бамбл
                        0. Назад""", EXIT, 2);
                switch (cold) {
                    case 1 -> addConfiguredDrink(DrinkType.ICE_LATTE, true);
                    case 2 -> addBumble();
                    default -> {
                    }
                }
            }
            default -> {
            }
        }
    }

    private void addMilkshake() {
        addFixedDrink("""
                Милкшейки:
                1. Классический
                2. Кофейный
                3. Шоколадный
                0. Назад""",
                List.of(
                        DrinkType.CLASSIC_MILKSHAKE,
                        DrinkType.COFFEE_MILKSHAKE,
                        DrinkType.CHOCOLATE_MILKSHAKE
                ),
                false
        );
    }

    private void addLemonade() {
        addFixedDrink("""
                Лимонады:
                1. Дыня-Малина
                2. Манго-Маракуйя
                3. Персик-Просекко
                4. Мохито
                0. Назад""",
                List.of(
                        DrinkType.MELON_RASPBERRY_LEMONADE,
                        DrinkType.MANGO_PASSION_LEMONADE,
                        DrinkType.PEACH_PROSECCO_LEMONADE,
                        DrinkType.MOJITO_LEMONADE
                ),
                false
        );
    }

    private void addTea() {
        int sub = input.choice(
                """
                        Чай:
                        1. Рассыпной
                        2. Натуральный
                        0. Назад""",
                EXIT, 2
        );

        switch (sub) {
            case 1 -> addConfiguredDrink(DrinkType.LOOSE_TEA, true);
            case 2 -> {
                int flavor = input.choice("""
                        Натуральный чай:
                        1. Облепиховый
                        2. Имбирный
                        3. Малиновый
                        4. Клюквенный
                        0. Назад""", EXIT, 4);
                switch (flavor) {
                    case 1 -> addConfiguredDrink(DrinkType.SEA_BUCKTHORN_TEA, true);
                    case 2 -> addConfiguredDrink(DrinkType.GINGER_TEA, true);
                    case 3 -> addConfiguredDrink(DrinkType.RASPBERRY_TEA, true);
                    case 4 -> addConfiguredDrink(DrinkType.CRANBERRY_TEA, true);
                    default -> {
                    }
                }
            }
            default -> {
            }
        }
    }

    private void addMatcha() {
        int sub = input.choice(
                """
                        Матча:
                        1. Горячая
                        2. Айс Матча
                        0. Назад""",
                EXIT, 2
        );
        if (sub == EXIT) {
            return;
        }

        String matchaPrefix = sub == 1 ? "Горячая матча" : "Айс матча";
        BigDecimal basePrice = sub == 1 ? new BigDecimal("60.00") : new BigDecimal("58.00");

        int milkType = input.choice("""
                Молоко:
                1. Обычное молоко
                2. Альтернативное молоко
                0. Назад""", EXIT, 2);
        if (milkType == EXIT) {
            return;
        }

        String milkName;
        BigDecimal milkExtra = BigDecimal.ZERO;
        if (milkType == 1) {
            milkName = "обычное молоко";
        } else {
            int alt = input.choice("""
                    Альтернативное молоко:
                    1. Кокосовое
                    2. Банановое
                    3. Другое
                    0. Назад""", EXIT, 3);
            if (alt == EXIT) {
                return;
            }
            milkName = switch (alt) {
                case 1 -> "кокосовое молоко";
                case 2 -> "банановое молоко";
                default -> "другое молоко";
            };
            milkExtra = new BigDecimal("4.00");
        }

        addCustomDrink(
                matchaPrefix + " на " + milkName,
                Area.MATCHA,
                basePrice.add(milkExtra),
                true,
                false,
                builder -> {
                }
        );
    }

    private void addStandaloneSyrup() {
        int syrup = input.choice("""
                Сиропы:
                1. Карамель
                2. Солёная карамель
                3. Вишня
                4. Банан
                5. Лесной орех
                6. Шоколад
                0. Назад""",
                EXIT, 6);
        if (syrup == EXIT) {
            return;
        }
        int count = input.choice("Количество сиропа (1-10):", 1, Limit.MAX_SUGAR);

        final int selectedSyrup = syrup;
        final int selectedCount = count;
        addCustomDrink(
                "Сироп",
                Area.SYRUPS,
                new BigDecimal("0.00"),
                true,
                false,
                builder -> builder.syrup(SYRUP_OPTIONS.get(selectedSyrup - 1), selectedCount)
        );
    }

    private void addBumble() {
        int juice = input.choice("""
                Бамбл:
                1. Апельсиновый сок
                2. Ананасовый сок
                3. Вишнёвый сок
                4. Гранатовый сок
                0. Назад""",
                EXIT, 4);
        if (juice == EXIT) {
            return;
        }
        int syrup = input.choice("""
                Сироп к бамблу:
                1. Карамель
                2. Солёная карамель
                3. Вишня
                4. Банан
                5. Лесной орех
                6. Шоколад
                0. Без сиропа""",
                EXIT, 6);

        int syrupCount = 0;
        if (syrup != EXIT) {
            syrupCount = input.choice("Количество сиропа (1-10):", 1, Limit.MAX_SUGAR);
        }

        final int selectedSyrup = syrup;
        final int selectedSyrupCount = syrupCount;
        addCustomDrink(
                "Бамбл",
                Area.COFFEE,
                new BigDecimal("62.00"),
                true,
                false,
                builder -> {
                    builder.juice(JUICE_OPTIONS.get(juice - 1));
                    if (selectedSyrup != EXIT) {
                        builder.syrup(SYRUP_OPTIONS.get(selectedSyrup - 1), selectedSyrupCount);
                    }
                }
        );
    }

    private void addConfiguredDrink(DrinkType drinkType, boolean askProducer) {
        drinkBuilder.type(drinkType);
        applyProducerIfNeeded(askProducer);
        configureSweetnessAndSyrup(true);
        orderBuilder.addDrink(drinkBuilder.build());
    }

    private void addCustomDrink(String name, Area area, BigDecimal cost, boolean askProducer, boolean allowSweetener) {
        addCustomDrink(name, area, cost, askProducer, allowSweetener, builder -> {
        });
    }

    private void addCustomDrink(String name, Area area, BigDecimal cost, boolean askProducer, boolean allowSweetener, java.util.function.Consumer<DrinkBuilder> customizer) {
        drinkBuilder.custom(name, area, cost);
        applyProducerIfNeeded(askProducer);
        customizer.accept(drinkBuilder);
        configureSweetnessAndSyrup(allowSweetener);
        orderBuilder.addDrink(drinkBuilder.build());
    }

    private void applyProducerIfNeeded(boolean askProducer) {
        if (!askProducer) {
            return;
        }
        ProducerStrategy producerStrategy = selectProducerForDrink();
        if (producerStrategy == null) {
            return;
        }
        drinkBuilder.producer(producerStrategy);
    }

    private ProducerStrategy selectProducerForDrink() {
        int choice = input.choice(
                """
                        Выберите производителя для напитка:
                        1. Качественный
                        2. Средний (экономный)
                        0. Назад""",
                EXIT, 2
        );
        return switch (choice) {
            case 1 -> new QualityProducerStrategy();
            case 2 -> new EconomyProducerStrategy();
            default -> null;
        };
    }

    private void configureSweetnessAndSyrup(boolean allowSweetener) {
        int sweetChoice = input.choice(
                allowSweetener
                        ? """
                        Добавить подсластитель?
                        0. Нет
                        1. Сахар
                        2. Сахарозаменитель"""
                        : """
                        Добавить сахар?
                        0. Нет
                        1. Сахар""",
                0,
                allowSweetener ? 2 : 1
        );

        if (sweetChoice == 1) {
            int count = input.choice("Количество сахара (1-10):", 1, Limit.MAX_SUGAR);
            drinkBuilder.sugars(count);
        } else if (sweetChoice == 2 && allowSweetener) {
            int count = input.choice("Количество сахарозаменителя (1-10):", 1, Limit.MAX_SUGAR);
            drinkBuilder.sweetener(count);
        }

        int syrupChoice = input.choice(
                """
                        Добавить сироп?
                        0. Нет
                        1. Карамель
                        2. Солёная карамель
                        3. Вишня
                        4. Банан
                        5. Лесной орех
                        6. Шоколад""",
                0, 6
        );
        if (syrupChoice != 0) {
            int count = input.choice("Количество сиропа (1-10):", 1, Limit.MAX_SUGAR);
            drinkBuilder.syrup(SYRUP_OPTIONS.get(syrupChoice - 1), count);
        }
    }

    private void addFixedDrink(String prompt, List<DrinkType> items, boolean askProducer) {
        int choice = input.choice(prompt, EXIT, items.size());
        if (choice == EXIT) {
            return;
        }
        addConfiguredDrink(items.get(choice - 1), askProducer);
    }

    private void showCurrent() {
        System.out.println();
        System.out.println(orderBuilder.currentText());
        System.out.println("Итого: " + Money.format(orderBuilder.totalCost()));
        System.out.println("Позиций: " + orderBuilder.itemCount());
    }

    private void finish() {
        System.out.println();
        System.out.println("Ваш заказ:");
        System.out.println(orderBuilder.currentText());
        System.out.println("Стратегия расчёта: " + orderBuilder.strategyName());
        System.out.println("Итого: " + Money.format(orderBuilder.totalCost()));
        System.out.println("Позиций: " + orderBuilder.itemCount());
        System.out.println("Спасибо за заказ!");
        orderBuilder.reset();
    }

    private PriceStrategy selectPriceStrategy() {
        int choice = input.choice(
                """
                        Выберите стратегию расчёта:
                        1. Стандартная
                        2. Оптовая (скидка 10% от 4 позиций)
                        0. Назад""",
                EXIT, 2
        );
        return switch (choice) {
            case 2 -> new BulkStrategy();
            case 1 -> new StandardStrategy();
            default -> new StandardStrategy();
        };
    }

    private void printMain() {
        System.out.println();
        System.out.println("Меню:");
        System.out.println("1. Добавить напиток");
        System.out.println("2. Изменить стратегию расчёта");
        System.out.println("3. Показать заказ");
        System.out.println("4. Оформить заказ");
        System.out.println("0. Выход");
    }
}
