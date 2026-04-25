/*
 * Провайдер фабрик напитков.
 * Возвращает нужную фабрику по выбранному производителю.
 */
package ru.ma1ko.cafeteria.factory;

import ru.ma1ko.cafeteria.domain.Producer;

public final class FactoryProvider {
    private FactoryProvider() {
    }

    public static Factory create(Producer producer) {
        if (producer == Producer.QUALITY) {
            return new QualityFactory();
        }
        return new EconomyFactory();
    }
}
