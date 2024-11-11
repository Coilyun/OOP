package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки корректности класса Card.
 * Проверяются создание карты, значение ранга и масти, 
 * а также вывод метода toString.
 */
public class CardTest {

    /**
     * Проверяет создание карты с королем и его атрибуты:
     * масть, ранг и значение.
     */
    @Test
    public void testCardCreation() {
        // Создание карты с королем
        Card card = new Card("K", "Пики");
        assertEquals("Пики", card.getSuit(), "Масть карты должна быть 'Пики'");
        assertEquals("K", card.getRank(), "Ранг карты должен быть 'K'");
        assertEquals(10, card.getValue(), "Значение короля должно быть 10");
    }

    /**
     * Проверяет правильность вывода карты в виде строки.
     */
    @Test
    public void testToString() {
        // Создание карты с дамой
        Card card = new Card("Q", "Червы");
        assertEquals("Q Червы", card.toString(), 
                     "Метод toString должен возвращать 'Q Червы'");
    }

    /**
     * Проверяет создание карты с числовым значением и его атрибуты.
     */
    @Test
    public void testCardWithNumber() {
        // Создание карты с числом
        Card card = new Card("2", "Трефы");
        assertEquals("Трефы", card.getSuit(), "Масть карты должна быть 'Трефы'");
        assertEquals("2", card.getRank(), "Ранг карты должен быть '2'");
        assertEquals(2, card.getValue(), "Значение карты '2' должно быть 2");
    }

    /**
     * Проверяет создание карты с тузом и корректное значение атрибутов.
     */
    @Test
    public void testCardWithAce() {
        // Создание карты с тузом
        Card card = new Card("A", "Пики");
        assertEquals("Пики", card.getSuit(), "Масть карты должна быть 'Пики'");
        assertEquals("A", card.getRank(), "Ранг карты должен быть 'A'");
        assertEquals(11, card.getValue(), "Значение туза должно быть 11");
    }
}
