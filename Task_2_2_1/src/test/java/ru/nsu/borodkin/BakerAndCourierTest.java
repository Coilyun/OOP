package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class BakerAndCourierTest {

    @Test
    public void testBakerAndCourierFlow() throws Exception {
        OrderQueue queue = new OrderQueue(10);
        Warehouse warehouse = new Warehouse(10);

        AtomicBoolean delivered = new AtomicBoolean(false);

        // Переопределяем курьера, чтобы он отметил доставку
        Courier courier = new Courier(2, warehouse, 0) {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        var orders = warehouse.takeUpTo(2);
                        for (Order order : orders) {
                            delivered.set(true); // помечаем доставку
                        }
                        Thread.sleep(100); // имитация доставки
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };

        Baker baker = new Baker(100, queue, warehouse, 0);
        baker.start();
        courier.start();

        // Кладем заказ
        queue.put(new Order(1));

        Thread.sleep(500); // ждём, пока всё произойдёт

        baker.interrupt();
        courier.interrupt();
        baker.join();
        courier.join();

        assertTrue(delivered.get(), "Курьер не доставил заказ");
    }
}
