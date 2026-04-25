/*
 * Контракт наблюдателя в паттерне Observer.
 * Реализуется классами, которые реагируют на изменения заказа.
 */
package ru.ma1ko.cafeteria.observer;

public interface Observer {
    void onChange(Snapshot snapshot);
}
