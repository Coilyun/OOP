package ru.nsu.borodkin.blackjack;

import org.junit.jupiter.api.Test;
import ru.nsu.borodkin.blackjack.Dealer;
import ru.nsu.borodkin.blackjack.Deck;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для проверки корректности поведения дилера в игре.
 * Проверяется метод play, который позволяет дилеру делать ход.
 */
public class DealerTest {

    /**
     * Проверяет логику хода дилера:
     * дилер должен брать карты, пока не наберет 17 очков или больше.
     */
    @Test
    public void testDealerPlay() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());

        int initialScore = dealer.getScore();

        dealer.play(deck);

        assertTrue(dealer.getScore() >= 17, 
                   "Дилер должен иметь хотя бы 17 очков");

        assertTrue(dealer.getScore() >= initialScore, 
                   "Дилер должен улучшить свой счет");
    }
}
