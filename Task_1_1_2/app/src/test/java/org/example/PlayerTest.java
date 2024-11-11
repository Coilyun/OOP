package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    public void testAddCard() {
        // Создаем игрока с именем "Игрок"
        Player player = new Player("Игрок");
        // Создаем карту
        Card card = new Card("8", "Червы");  // Указываем карту с номером
        // Добавляем карту игроку
        player.receiveCard(card);
        
        // Проверяем, что карта добавлена в руку
        assertEquals(1, player.getHand().size());
        // Проверяем, что очки игрока равны 8
        assertEquals(8, player.getScore());
    }

    @Test
    public void testCalculateHandValueWithAce() {
        // Создаем игрока с именем "Игрок"
        Player player = new Player("Игрок");
        // Добавляем карты игроку
        player.receiveCard(new Card("A", "Пики"));
        player.receiveCard(new Card("8", "Червы"));
        
        // Проверяем, что сумма очков правильная
        assertEquals(19, player.getScore());
    }

    @Test
    public void testCalculateHandValueWithAceExceeding21() {
        // Создаем игрока с именем "Игрок"
        Player player = new Player("Игрок");
        // Добавляем карты игроку
        player.receiveCard(new Card("A", "Пики"));
        player.receiveCard(new Card("8", "Червы"));
        player.receiveCard(new Card("5", "Трефы"));
        
        // Проверяем, что сумма очков правильная (Туз должен быть равен 1)
        assertEquals(14, player.getScore());
    }
}
