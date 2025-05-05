package ru.nsu.borodkin;

import java.util.LinkedList;
import java.util.List;

public class Warehouse {
    private final LinkedList<Order> storage = new LinkedList<>();
    private final int capacity;

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(Order order) throws InterruptedException {
        while (storage.size() >= capacity) {
            wait();
        }
        storage.addLast(order);
        notifyAll();
    }

    public synchronized List<Order> takeUpTo(int maxCount) throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        List<Order> result = new LinkedList<>();
        while (!storage.isEmpty() && result.size() < maxCount) {
            result.add(storage.removeFirst());
        }
        notifyAll();
        return result;
    }
}
