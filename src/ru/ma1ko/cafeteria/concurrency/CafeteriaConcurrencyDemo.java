package ru.ma1ko.cafeteria.concurrency;

import java.util.ArrayList;
import java.util.List;

public final class CafeteriaConcurrencyDemo {
    private static final int CLIENT_COUNT = 3;
    private static final int REQUESTS_PER_CLIENT = 4;
    private static final int BARISTA_COUNT = 2;

    private CafeteriaConcurrencyDemo() {
    }

    public static void runDemo() {
        System.out.println();
        System.out.println("=== Concurrency demo: cafeteria request queue ===");
        CafeteriaServer server = new CafeteriaServer(BARISTA_COUNT);
        server.start();

        List<Thread> clientThreads = startClients(server);
        waitForClients(clientThreads);
        server.stopWhenQueueIsEmpty();
        server.waitUntilStopped();

        int plannedRequests = CLIENT_COUNT * REQUESTS_PER_CLIENT;
        System.out.println("Processed planned requests: " + plannedRequests);
        System.out.println("=== Concurrency demo finished ===");
    }

    private static List<Thread> startClients(CafeteriaServer server) {
        List<Thread> clientThreads = new ArrayList<>();
        for (int i = 1; i <= CLIENT_COUNT; i++) {
            String clientName = "client-" + i;
            Thread thread = new Thread(
                    new OrderGenerator(clientName, server, REQUESTS_PER_CLIENT),
                    clientName + "-thread"
            );
            clientThreads.add(thread);
            thread.start();
        }
        return clientThreads;
    }

    private static void waitForClients(List<Thread> clientThreads) {
        for (Thread clientThread : clientThreads) {
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[main] interrupted while waiting clients");
                return;
            }
        }
        System.out.println("[main] all clients finished generation");
    }
}
