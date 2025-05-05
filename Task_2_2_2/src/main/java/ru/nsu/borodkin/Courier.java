package ru.nsu.borodkin;

import java.util.List;

public class Courier extends Thread {
    private final int capacity;
    private final Warehouse warehouse;

    public Courier(int capacity, Warehouse warehouse, int id) {
        super("Courier-" + id);
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                List<Order> orders = warehouse.takeUpTo(capacity);
                for (Order order : orders) {
                    System.out.println("[" + order.getId() + "] доставляется курьером " + getName());
                }
                Thread.sleep(3000);
                for (Order order : orders) {
                    System.out.println("[" + order.getId() + "] доставлена");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " завершает работу");
        }
    }
}
