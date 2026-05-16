package ru.ma1ko.cafeteria.concurrency;

import ru.ma1ko.cafeteria.decorator.Drink;

import java.util.Objects;

public final class OrderRequestBuilder {
    private int requestId;
    private String clientName;
    private Drink drink;

    public OrderRequestBuilder requestId(int requestId) {
        if (requestId < 1) {
            throw new IllegalArgumentException("Request id must be positive");
        }
        this.requestId = requestId;
        return this;
    }

    public OrderRequestBuilder clientName(String clientName) {
        this.clientName = Objects.requireNonNull(clientName, "clientName");
        return this;
    }

    public OrderRequestBuilder drink(Drink drink) {
        this.drink = Objects.requireNonNull(drink, "drink");
        return this;
    }

    public OrderRequest build() {
        if (requestId < 1) {
            throw new IllegalStateException("Request id is not set");
        }
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalStateException("Client name is not set");
        }
        if (drink == null) {
            throw new IllegalStateException("Drink is not set");
        }
        return new OrderRequest(requestId, clientName, drink, false);
    }
}
