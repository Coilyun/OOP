package com.example.blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlackjackGameTest {
    @Test
    public void testInitialDeal() {
        BlackjackGame game = new BlackjackGame();
        game.playRound();

        assertEquals(2, game.getPlayer().getHand().size());
        assertEquals(2, game.getDealer().getHand().size());
    }

    @Test
    public void testPlayerBlackjackWin() {
        BlackjackGame game = new BlackjackGame();
        Player player = game.getPlayer();
        Player dealer = game.getDealer();

        // Подготовка состояния
        player.addCard(new Card("Червы", "Туз", 11));
        player.addCard(new Card("Пики", "10", 10));
        dealer.addCard(new Card("Червы", "8", 8));
        dealer.addCard(new Card("Трефы", "7", 7));

        assertEquals(21, player.calculateHandValue());
        assertEquals(15, dealer.calculateHandValue());
        // Проверка что игра распознает победителя корректно
        game.determineWinner();
        assertEquals(1, game.getPlayerScore());
        assertEquals(0, game.getDealerScore());
    }
}
