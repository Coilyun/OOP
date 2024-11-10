package com.example.blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testAddCard() {
        Player player = new Player(false);
        Card card = new Card("Червы", "8", 8);
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals(8, player.calculateHandValue());
    }

    @Test
    public void testCalculateHandValueWithAce() {
        Player player = new Player(false);
        player.addCard(new Card("Пики", "Туз", 11));
        player.addCard(new Card("Червы", "8", 8));
        assertEquals(19, player.calculateHandValue());
    }

    @Test
    public void testCalculateHandValueWithAceExceeding21() {
        Player player = new Player(false);
        player.addCard(new Card("Пики", "Туз", 11));
        player.addCard(new Card("Червы", "8", 8));
        player.addCard(new Card("Трефы", "5", 5));
        assertEquals(14, player.calculateHandValue()); // Туз должен стать 1
    }
}
