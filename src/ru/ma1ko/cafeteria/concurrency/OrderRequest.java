package ru.ma1ko.cafeteria.concurrency;

import ru.ma1ko.cafeteria.decorator.Drink;

public final class OrderRequest {
    private final int requestId;
    private final String clientName;
    private final Drink drink;
    private final boolean stopSignal;

    OrderRequest(int requestId, String clientName, Drink drink, boolean stopSignal) {
        this.requestId = requestId;
        this.clientName = clientName;
        this.drink = drink;
        this.stopSignal = stopSignal;
    }

    public static OrderRequest stopRequest() {
        return new OrderRequest(-1, "system", null, true);
    }

    public int requestId() {
        return requestId;
    }

    public String clientName() {
        return clientName;
    }

    public Drink drink() {
        return drink;
    }

    public boolean isStopSignal() {
        return stopSignal;
    }

    @Override
    public String toString() {
        if (stopSignal) {
            return "StopRequest";
        }
        return "OrderRequest{" +
                "requestId=" + requestId +
                ", clientName='" + clientName + '\'' +
                ", drink='" + drink.description() + '\'' +
                '}';
    }
}
