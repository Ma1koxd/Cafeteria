package ru.ma1ko.cafeteria.concurrency;

import ru.ma1ko.cafeteria.util.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class CafeteriaServer {
    private final BlockingQueue<OrderRequest> requestQueue;
    private final List<Thread> workerThreads;
    private final int workerCount;
    private volatile boolean accepting;

    public CafeteriaServer(int workerCount) {
        if (workerCount < 1) {
            throw new IllegalArgumentException("Worker count must be positive");
        }
        this.requestQueue = new LinkedBlockingQueue<>();
        this.workerThreads = new ArrayList<>();
        this.workerCount = workerCount;
        this.accepting = true;
    }

    public void start() {
        if (!workerThreads.isEmpty()) {
            throw new IllegalStateException("Server already started");
        }
        System.out.println("[server] started, workers: " + workerCount);
        for (int i = 1; i <= workerCount; i++) {
            String workerName = "barista-" + i;
            Thread thread = new Thread(new Barista(workerName), workerName);
            workerThreads.add(thread);
            thread.start();
        }
    }

    public void addRequest(OrderRequest request) {
        Objects.requireNonNull(request, "request");
        if (!accepting) {
            throw new IllegalStateException("Server does not accept new requests");
        }
        try {
            requestQueue.put(request);
            System.out.printf("[server] accepted %s, queue size: %d%n", request, requestQueue.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[server] interrupted while accepting request");
        }
    }

    public void stopWhenQueueIsEmpty() {
        accepting = false;
        for (int i = 0; i < workerCount; i++) {
            try {
                requestQueue.put(OrderRequest.stopRequest());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[server] interrupted while sending stop signal");
                return;
            }
        }
    }

    public void waitUntilStopped() {
        for (Thread workerThread : workerThreads) {
            try {
                workerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[server] interrupted while waiting workers");
                return;
            }
        }
        System.out.println("[server] stopped");
    }

    private final class Barista implements Runnable {
        private final String name;

        private Barista(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("[" + name + "] ready");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    OrderRequest request = requestQueue.take();
                    if (request.isStopSignal()) {
                        System.out.println("[" + name + "] no more planned requests");
                        return;
                    }
                    process(request);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("[" + name + "] interrupted");
                }
            }
        }

        private void process(OrderRequest request) throws InterruptedException {
            System.out.printf(
                    "[%s] prepares request %d from %s: %s (%s)%n",
                    name,
                    request.requestId(),
                    request.clientName(),
                    request.drink().description(),
                    Money.format(request.drink().cost())
            );
            Thread.sleep(250L + request.drink().description().length() * 10L);
            System.out.printf("[%s] completed request %d from %s%n", name, request.requestId(), request.clientName());
        }
    }
}
