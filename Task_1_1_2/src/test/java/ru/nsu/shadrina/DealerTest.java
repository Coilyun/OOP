package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        // Создание дилера и колоды
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        // Дилер получает две карты
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());

        // Запоминаем начальный счет дилера
        int initialScore = dealer.getScore();

        // Дилер делает ход
        dealer.play(deck);

        // Проверка, что сумма очков дилера не меньше 17
        assertTrue(dealer.getScore() >= 17, 
                   "Дилер должен иметь хотя бы 17 очков");

        // Проверка, что после хода сумма очков увеличилась
        // (или осталась, если уже было 17 или больше)
        assertTrue(dealer.getScore() >= initialScore, 
                   "Дилер должен улучшить свой счет");
    }
}
