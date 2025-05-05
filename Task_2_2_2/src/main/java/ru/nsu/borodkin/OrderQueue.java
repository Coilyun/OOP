package ru.nsu.borodkin;

import java.util.LinkedList;

public class OrderQueue {
    private final LinkedList<Order> queue = new LinkedList<>();
    private final int capacity;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(Order order) throws InterruptedException {
        while (queue.size() >= capacity) {
            wait();
        }
        queue.addLast(order);
        notifyAll();
    }

    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Order order = queue.removeFirst();
        notifyAll();
        return order;
    }
}
