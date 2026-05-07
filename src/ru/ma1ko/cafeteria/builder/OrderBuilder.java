/*
 * Builder для создания заказа из выбранных позиций.
 * Формирует итоговый объект заказа для дальнейшей обработки.
 */
package ru.ma1ko.cafeteria.builder;

import ru.ma1ko.cafeteria.composite.Group;
import ru.ma1ko.cafeteria.composite.Item;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.Area;
import ru.ma1ko.cafeteria.observer.Observer;
import ru.ma1ko.cafeteria.observer.Snapshot;
import ru.ma1ko.cafeteria.strategy.PriceStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class OrderBuilder {
    private final Group root;
    private final EnumMap<Area, Group> areaToGroupMap;
    private final List<Observer> observerList;
    private final PriceStrategy defaultStrategy;
    private PriceStrategy strategy;

    public OrderBuilder(PriceStrategy defaultStrategy,
                        List<Observer> observerList) {
        this.root = new Group("Заказ");
        this.areaToGroupMap = new EnumMap<>(Area.class);
        this.observerList = new ArrayList<>(observerList);
        this.defaultStrategy = Objects.requireNonNull(defaultStrategy, "defaultStrategy");
        this.strategy = this.defaultStrategy;
    }

    public void setStrategy(PriceStrategy newStrategy) {
        this.strategy = Objects.requireNonNull(newStrategy, "newStrategy");
        notifyObservers("Стратегия расчёта изменена");
    }

    public void reset() {
        root.clear();
        areaToGroupMap.clear();
        strategy = defaultStrategy;
        notifyObservers("Заказ сброшен");
    }

    public void addDrink(Drink drink) {
        Drink safeDrink = Objects.requireNonNull(drink, "drink");
        Group group = areaToGroupMap.computeIfAbsent(safeDrink.area(), area -> {
            Group newGroup = new Group(area.displayName());
            root.add(newGroup);
            return newGroup;
        });
        group.add(new Item(safeDrink));
        notifyObservers("Добавлен напиток: " + safeDrink.description());
    }

    public BigDecimal totalCost() {
        return strategy.total(root);
    }

    public int itemCount() {
        return root.count();
    }

    public String currentText() {
        return root.text("");
    }

    public String strategyName() {
        return strategy.name();
    }

    private void notifyObservers(String event) {
        if (observerList.isEmpty()) {
            return;
        }
        Snapshot snapshot = new Snapshot(event, currentText(), totalCost(), itemCount(), strategyName());
        for (Observer observer : observerList) {
            observer.onChange(snapshot);
        }
    }
}
