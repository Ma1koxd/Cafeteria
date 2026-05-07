/*
 * Наблюдатель, выводящий итоговую стоимость заказа.
 */
package ru.ma1ko.cafeteria.observer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.ma1ko.cafeteria.util.Money;

@Component
@Scope("singleton")
public class CostObs implements Observer {
    @Override
    public void onChange(Snapshot snapshot) {
        System.out.println("Текущая сумма: " + Money.format(snapshot.total()));
    }
}
