package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {
    @Test
    public void testInitialDeal() {
        BlackjackGame game = new BlackjackGame("Игрок"); // Указываем имя игрока
        game.playRound();

        assertEquals(2, game.getPlayer().getHand().size());
        assertEquals(2, game.getDealer().getHand().size());
    }


    @Test
    public void testPlayerBlackjackWin() {
        BlackjackGame game = new BlackjackGame();
        Player player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Подготовка состояния
        player.receiveCard(new Card("A", "Червы"));  // Туз
        player.receiveCard(new Card("10", "Пики"));  // 10

        dealer.receiveCard(new Card("8", "Червы"));
        dealer.receiveCard(new Card("7", "Трефы"));

        assertEquals(21, player.getScore());  // Игрок должен иметь 21 очко (Blackjack)
        assertEquals(15, dealer.getScore()); // Дилер имеет 15 очков

        // Проверка, что игра распознает победителя
        game.determineWinner();
        assertEquals(1, game.getPlayerScore()); // Игрок выигрывает
        assertEquals(0, game.getDealerScore()); // Дилер не выигрывает
    }


}
