package ru.nsu.borodkin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PizzeriaConfig config = PizzeriaConfig.load("/config.json");

        OrderQueue orderQueue = new OrderQueue(100);
        Warehouse warehouse = new Warehouse(config.warehousesCapacity);

        List<Baker> bakers = new ArrayList<>();
        for (int i = 0; i < config.bakers.length; i++) {
            Baker baker = new Baker(config.bakers[i], orderQueue, warehouse, i);
            bakers.add(baker);
            baker.start();
        }

        List<Courier> couriers = new ArrayList<>();
        for (int i = 0; i < config.couriers.length; i++) {
            Courier courier = new Courier(config.couriers[i], warehouse, i);
            couriers.add(courier);
            courier.start();
        }

        int orderId = 1;
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < config.workTime) {
            Order order = new Order(orderId++);
            orderQueue.put(order);
            System.out.println("[" + order.getId() + "] заказ поступил");
            Thread.sleep(1000);
        }

        for (Baker baker : bakers) {
            baker.interrupt();
        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }

        for (Baker baker : bakers) {
            baker.join();
        }
        for (Courier courier : couriers) {
            courier.join();
        }

        System.out.println("Пиццерия закрыта");
    }
}
