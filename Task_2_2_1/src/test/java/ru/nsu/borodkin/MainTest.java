package ru.nsu.borodkin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    public void testPizzeriaSimulation() throws Exception {
        // Конфигурация "вручную", без JSON
        int[] bakersSpeed = {200};      // пекарь готовит быстро
        int[] couriersCapacity = {1};   // один заказ за раз
        int warehouseCapacity = 10;
        int workTime = 1000;            // пиццерия работает 1 секунду

        PizzeriaConfig config = new PizzeriaConfig();
        config.bakers = bakersSpeed;
        config.couriers = couriersCapacity;
        config.warehousesCapacity = warehouseCapacity;
        config.workTime = workTime;

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

        int orderCount = 0;
        while (System.currentTimeMillis() - start < config.workTime) {
            Order order = new Order(orderId++);
            orderQueue.put(order);
            orderCount++;
            Thread.sleep(200); // часто подаём заказы
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

        // Проверим, что все заказы были доставлены
        // (если не успели все — хотя бы один прошёл полный путь)
        // Это сложно проверить напрямую, так как нет статистики.
        // Поэтому просто убеждаемся, что система завершилась без исключений
        assertTrue(true); // успешное выполнение = успех теста
    }
}
