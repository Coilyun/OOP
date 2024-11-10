package com.example.blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void testCardCreation() {
        Card card = new Card("Пики", "Король", 10);
        assertEquals("Пики", card.getSuit());
        assertEquals("Король", card.getRank());
        assertEquals(10, card.getValue());
    }

    @Test
    public void testToString() {
        Card card = new Card("Червы", "Дама", 10);
        assertEquals("Дама Червы (10)", card.toString());
    }
}
