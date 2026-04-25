/*
 * Наблюдатель, выводящий подробности заказа.
 */
package ru.ma1ko.cafeteria.observer;

import ru.ma1ko.cafeteria.util.Money;

public final class DetailObs implements Observer {
    @Override
    public void onChange(Snapshot snapshot) {
        System.out.println();
        System.out.println("Событие: " + snapshot.event());
        System.out.println(snapshot.text());
        System.out.println("Стратегия: " + snapshot.strategy());
        System.out.println("Сумма: " + Money.format(snapshot.total()));
        System.out.println("Позиций: " + snapshot.count());
    }
}
