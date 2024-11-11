package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки корректности работы с игроком.
 * Включает проверку добавления карт, расчета очков с тузом и обработки перебора.
 */
public class PlayerTest {

    /**
     * Проверяет, что карта добавляется в руку игрока и корректно рассчитываются очки.
     */
    @Test
    public void testAddCard() {
        Player player = new Player("Игрок");
        Card card = new Card("8", "Червы");  // Карта с номиналом 8
        player.receiveCard(card);  // Добавляем карту в руку игрока
        
        // Проверка, что в руке 1 карта
        assertEquals(1, player.getHand().size());
        
        // Проверка, что очки игрока равны 8
        assertEquals(8, player.getScore());
    }

    /**
     * Проверяет, что расчет очков учитывает туз как 11, если это не приводит к перебору.
     */
    @Test
    public void testCalculateHandValueWithAce() {
        Player player = new Player("Игрок");
        player.receiveCard(new Card("A", "Пики"));  // Туз (A)
        player.receiveCard(new Card("8", "Червы")); // Карта с номиналом 8
        
        // Проверка, что сумма очков правильная (11 + 8 = 19)
        assertEquals(19, player.getScore());
    }

    /**
     * Проверяет, что расчет очков корректно изменяет туз на 1, если это необходимо,
     * чтобы избежать перебора (сумма очков больше 21).
     */
    @Test
    public void testCalculateHandValueWithAceExceeding21() {
        Player player = new Player("Игрок");
        player.receiveCard(new Card("A", "Пики"));  // Туз (A)
        player.receiveCard(new Card("8", "Червы")); // Карта с номиналом 8
        player.receiveCard(new Card("5", "Трефы")); // Карта с номиналом 5
        
        // Проверка, что сумма очков правильная (Туз должен быть равен 1, так как перебор)
        assertEquals(14, player.getScore());
    }
}
