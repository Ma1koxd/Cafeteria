package ru.ma1ko.cafeteria.concurrency;

import ru.ma1ko.cafeteria.builder.DrinkBuilder;
import ru.ma1ko.cafeteria.decorator.Drink;
import ru.ma1ko.cafeteria.domain.DrinkType;
import ru.ma1ko.cafeteria.domain.Producer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class OrderGenerator implements Runnable {
    private static final List<String> SYRUPS = List.of(
            "caramel",
            "cherry",
            "banana",
            "chocolate"
    );
    private static final DrinkType[] DRINK_TYPES = DrinkType.values();
    private static final Producer[] PRODUCERS = Producer.values();

    private final String clientName;
    private final CafeteriaServer server;
    private final int numberOfRequests;

    public OrderGenerator(String clientName, CafeteriaServer server, int numberOfRequests) {
        if (numberOfRequests < 1) {
            throw new IllegalArgumentException("Request count must be positive");
        }
        this.clientName = Objects.requireNonNull(clientName, "clientName");
        this.server = Objects.requireNonNull(server, "server");
        this.numberOfRequests = numberOfRequests;
    }

    @Override
    public void run() {
        System.out.println("[" + clientName + "] started request generation");
        DrinkBuilder drinkBuilder = new DrinkBuilder();
        for (int i = 1; i <= numberOfRequests; i++) {
            OrderRequest request = new OrderRequestBuilder()
                    .requestId(i)
                    .clientName(clientName)
                    .drink(buildDrink(drinkBuilder))
                    .build();
            server.addRequest(request);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(120, 300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[" + clientName + "] interrupted");
                return;
            }
        }
        System.out.println("[" + clientName + "] finished request generation");
    }

    private Drink buildDrink(DrinkBuilder drinkBuilder) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        drinkBuilder.type(DRINK_TYPES[random.nextInt(DRINK_TYPES.length)]);
        drinkBuilder.producer(PRODUCERS[random.nextInt(PRODUCERS.length)]);

        int sweetness = random.nextInt(3);
        if (sweetness == 1) {
            drinkBuilder.sugars(random.nextInt(2) + 1);
        } else if (sweetness == 2) {
            drinkBuilder.sweetener(random.nextInt(2) + 1);
        }

        if (random.nextBoolean()) {
            drinkBuilder.syrup(SYRUPS.get(random.nextInt(SYRUPS.size())), random.nextInt(2) + 1);
        }

        return drinkBuilder.build();
    }
}
