package ru.nsu.borodkin.blackjack;

import org.junit.jupiter.api.Test;
import ru.nsu.borodkin.blackjack.Card;
import ru.nsu.borodkin.blackjack.Deck;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 * Тесты для проверки корректности работы с колодой карт.
 * Включает проверку размера колоды, извлечения карты и тасования.
 */
public class DeckTest {

    /**
     * Проверяет начальный размер колоды.
     * Колода должна содержать 52 карты.
     */
    @Test
    public void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.remainingCards(), "Колода должна содержать 52 карты");
    }

    /**
     * Проверяет корректность извлечения карты из колоды.
     * После извлечения карты в колоде должно остаться 51 карта.
     */
    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        
        assertNotNull(card, "Карта не может быть пустой");
        
        assertEquals(51, deck.remainingCards(), 
                     "После взятия карты из колоды в ней должно быть 51 карта");
    }

    /**
     * Проверяет, что колода меняется после тасования.
     * После тасования порядок карт должен отличаться от начального.
     */
    @Test
    public void testDeckShuffle() {
        Deck deck = new Deck();
        List<Card> cardsBeforeShuffle = new ArrayList<>(deck.getCards());
        
        deck.shuffleDeck();
        
        List<Card> cardsAfterShuffle = deck.getCards();

        assertNotEquals(cardsBeforeShuffle, cardsAfterShuffle, 
                        "Колода должна измениться после тасования");
    }

}
