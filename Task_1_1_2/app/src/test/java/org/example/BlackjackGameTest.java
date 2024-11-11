package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {
    @Test
    public void testInitialDeal() {
        BlackjackGame game = new BlackjackGame("Игрок");
        
        // Вместо playRound можно вручную раздать карты для отладки
        game.getDealer().receiveCard(new Card("A", "Пики"));
        game.getDealer().receiveCard(new Card("K", "Пики"));
        game.getPlayer().receiveCard(new Card("10", "Червы"));
        game.getPlayer().receiveCard(new Card("7", "Трефы"));

        // Проверяем, что у игрока и дилера по 2 карты
        assertEquals(2, game.getPlayer().getHand().size(), "Игрок должен получить 2 карты");
        assertEquals(2, game.getDealer().getHand().size(), "Дилер должен получить 2 карты");
    }


    @Test
    public void testPlayerBlackjackWin() {
        BlackjackGame game = new BlackjackGame();
        Player player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Подготовка состояния
        player.receiveCard(new Card("A", "Червы"));
        player.receiveCard(new Card("10", "Пики"));
        dealer.receiveCard(new Card("8", "Червы"));
        dealer.receiveCard(new Card("7", "Трефы"));

        assertEquals(21, player.getScore());
        assertEquals(15, dealer.getScore());

        // Проверка, что игра распознает победителя
        game.determineWinner();
        assertEquals(1, game.getPlayerGameScore());
        assertEquals(0, game.getDealerGameScore());
    }
}
