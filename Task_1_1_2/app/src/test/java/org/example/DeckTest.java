package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DeckTest {

    @Test
    public void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.remainingCards(), "Колода должна содержать 52 карты");
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        assertNotNull(card, "Карта не может быть пустой");
        assertEquals(51, deck.remainingCards(), "После взятия карты из колоды в ней должно быть 51 карта");
    }

    @Test
    public void testDeckShuffle() {
        Deck deck = new Deck();
        List<Card> cardsBeforeShuffle = List.copyOf(deck.getCards());
        deck.shuffleDeck();
        List<Card> cardsAfterShuffle = deck.getCards();

        // Колода после тасования должна отличаться от первоначальной
        assertNotEquals(cardsBeforeShuffle, cardsAfterShuffle, "Колода должна измениться после тасования");
    }
}
