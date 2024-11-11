package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    @Test
    public void testDealerPlay() {
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
        assertTrue(dealer.getScore() >= 17, "Дилер должен иметь хотя бы 17 очков");

        // Проверка, что после хода сумма очков увеличилась (или осталась, если уже было 17+)
        assertTrue(dealer.getScore() >= initialScore, "Дилер должен улучшить свой счет");
    }
}
