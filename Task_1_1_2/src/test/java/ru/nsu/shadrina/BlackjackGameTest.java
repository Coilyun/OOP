package ru.nsu.shadrina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Тесты для проверки логики игры в Blackjack.
 * Проверяются начальная раздача, а также условия выигрыша при различных комбинациях карт.
 */
public class BlackjackGameTest {

    /**
     * Проверяет, что при начальной раздаче каждому игроку дается по 2 карты.
     */
    @Test
    public void testInitialDeal() {
        BlackjackGame game = new BlackjackGame("Игрок");

        // Вручную задаем карты для дилера и игрока для отладки
        game.getDealer().receiveCard(new Card("A", "Пики"));
        game.getDealer().receiveCard(new Card("K", "Пики"));
        game.getPlayer().receiveCard(new Card("10", "Червы"));
        game.getPlayer().receiveCard(new Card("7", "Трефы"));

        // Проверяем, что у игрока и дилера по 2 карты
        assertEquals(2, game.getPlayer().getHand().size(), "Игрок должен получить 2 карты");
        assertEquals(2, game.getDealer().getHand().size(), "Дилер должен получить 2 карты");
    }

    /**
     * Проверяет, что игра правильно определяет победу игрока при наличии блэкджека.
     */
    @Test
    public void testPlayerBlackjackWin() {
        BlackjackGame game = new BlackjackGame();
        Player player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Устанавливаем карты, при которых игрок получает Blackjack
        player.receiveCard(new Card("A", "Червы"));
        player.receiveCard(new Card("10", "Пики"));
        dealer.receiveCard(new Card("8", "Червы"));
        dealer.receiveCard(new Card("7", "Трефы"));

        // Проверяем, что у игрока Blackjack и меньше 21 у дилера
        assertEquals(21, player.getScore());
        assertEquals(15, dealer.getScore());

        // Проверка на то, что игра определяет победителя правильно
        game.determineWinner();
        assertEquals(1, game.getPlayerGameScore(), "Игрок должен выиграть с Blackjack");
        assertEquals(0, game.getDealerGameScore(), "Дилер не должен получить очков");
    }

    /**
     * Проверяет, что игра правильно определяет ничью при равных очках.
     */
    @Test
    public void testDraw() {
        BlackjackGame game = new BlackjackGame();
        Player player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Устанавливаем карты, при которых игрок и дилер получают одинаковое количество очков
        player.receiveCard(new Card("8", "Червы"));
        player.receiveCard(new Card("7", "Пики"));

        dealer.receiveCard(new Card("10", "Трефы"));
        dealer.receiveCard(new Card("5", "Пики"));

        // Проверяем, что у игрока и дилера одинаковое количество очков
        assertEquals(15, player.getScore());
        assertEquals(15, dealer.getScore());

        // Проверка на то, что игра определяет ничью
        game.determineWinner();
        assertEquals(0, game.getPlayerGameScore(), "Ничья, у игрока не должно быть очков");
        assertEquals(0, game.getDealerGameScore(), "Ничья, у дилера не должно быть очков");
    }
    
}
