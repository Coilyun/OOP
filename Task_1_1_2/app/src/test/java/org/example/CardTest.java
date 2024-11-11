package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardCreation() {
        // Создание карты с королем
        Card card = new Card("K", "Пики");
        assertEquals("Пики", card.getSuit());    // Проверка масти
        assertEquals("K", card.getRank());       // Проверка ранга
        assertEquals(10, card.getValue());       // Король должен иметь значение 10
    }

    @Test
    public void testToString() {
        // Создание карты с дамой
        Card card = new Card("Q", "Червы");
        assertEquals("Q Червы", card.toString()); // Проверка корректности метода toString
    }

    @Test
    public void testCardWithNumber() {
        // Создание карты с числом
        Card card = new Card("2", "Трефы");
        assertEquals("Трефы", card.getSuit());  // Проверка масти
        assertEquals("2", card.getRank());     // Проверка ранга
        assertEquals(2, card.getValue());      // Проверка значения карты (для "2")
    }

    @Test
    public void testCardWithAce() {
        // Создание карты с Тузом
        Card card = new Card("A", "Пики");
        assertEquals("Пики", card.getSuit());    // Проверка масти
        assertEquals("A", card.getRank());       // Проверка ранга
        assertEquals(11, card.getValue());      // Туз должен иметь значение 11
    }
}
