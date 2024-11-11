package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardCreation() {
        Card card = new Card("Король", "Пики");  // Изменили конструктор
        assertEquals("Пики", card.getSuit());
        assertEquals("Король", card.getRank());
        assertEquals(10, card.getValue());  // Ожидаемое значение для Короля
    }

    @Test
    public void testToString() {
        Card card = new Card("Дама", "Червы");
        assertEquals("Дама Червы", card.toString());
    }
    @Test
    public void testCardWithNumber() {
        Card card = new Card("2", "Трефы");
        assertEquals("Трефы", card.getSuit());
        assertEquals("2", card.getRank());
        assertEquals(2, card.getValue());  // Ожидаемое значение для карты "2"
}

}
