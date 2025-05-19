package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    @Test
    public void testPutAndTake() throws InterruptedException {
        Warehouse warehouse = new Warehouse(5);
        Order order = new Order(1);
        warehouse.put(order);

        List<Order> taken = warehouse.takeUpTo(2);
        assertEquals(1, taken.size());
        assertEquals(1, taken.get(0).getId());
    }

    @Test
    public void testCapacityLimit() throws InterruptedException {
        Warehouse warehouse = new Warehouse(1);
        warehouse.put(new Order(1));

        Thread t = new Thread(() -> {
            try {
                warehouse.put(new Order(2)); // должен блокироваться
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t.start();
        Thread.sleep(100); // поток должен быть заблокирован
        assertTrue(t.isAlive());

        warehouse.takeUpTo(1); // освободим место
        t.join(1000);
        assertFalse(t.isAlive()); // поток должен завершиться
    }
}
