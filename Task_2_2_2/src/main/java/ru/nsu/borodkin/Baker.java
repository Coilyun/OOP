package ru.nsu.borodkin;

public class Baker extends Thread {
    private final int speed;
    private final OrderQueue orderQueue;
    private final Warehouse warehouse;

    public Baker(int speed, OrderQueue orderQueue, Warehouse warehouse, int id) {
        super("Baker-" + id);
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Order order = orderQueue.take();
                System.out.println("[" + order.getId() + "] готовится пекарем " + getName());
                Thread.sleep(speed);
                System.out.println("[" + order.getId() + "] готова");
                warehouse.put(order);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " завершает работу");
        }
    }
}
