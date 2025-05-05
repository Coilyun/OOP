package ru.nsu.borodkin.workers;

import ru.nsu.borodkin.model.Order;
import ru.nsu.borodkin.model.Storage;

public class Courier implements Runnable {
    private final int capacity;
    private final Storage storage;

    public Courier(int capacity, Storage storage) {
        this.capacity = capacity;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = storage.take();
                System.out.println(order.getId() + " Доставляется");
                Thread.sleep(3000);
                System.out.println(order.getId() + " Доставлен");
            }
        } catch (InterruptedException ignored) {}
    }
}
