/*
 * Наблюдатель, выводящий итоговую стоимость заказа.
 */
package ru.ma1ko.cafeteria.observer;

import ru.ma1ko.cafeteria.util.Money;

public final class CostObs implements Observer {
    @Override
    public void onChange(Snapshot snapshot) {
        System.out.println("Текущая сумма: " + Money.format(snapshot.total()));
    }
}
