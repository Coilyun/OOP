package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderQueueTest {

    @Test
    public void testPutAndTake() throws InterruptedException {
        OrderQueue queue = new OrderQueue(10);
        Order order = new Order(1);
        queue.put(order);
        Order result = queue.take();
        assertEquals(1, result.getId());
    }

    @Test
    public void testBlockingBehavior() throws InterruptedException {
        OrderQueue queue = new OrderQueue(1);
        queue.put(new Order(1));

        Thread t = new Thread(() -> {
            try {
                queue.put(new Order(2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.start();
        Thread.sleep(100); // поток должен быть заблокирован
        assertTrue(t.isAlive());

        queue.take(); // освободим место
        t.join(1000);
        assertFalse(t.isAlive()); // поток должен завершиться
    }
}
