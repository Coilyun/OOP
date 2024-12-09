package ru.nsu.borodkin.blackjack;

import org.junit.jupiter.api.Test;
import ru.nsu.borodkin.blackjack.Card;
import ru.nsu.borodkin.blackjack.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для проверки корректности работы с игроком.
 * Включает проверку добавления карт, расчета очков с тузом и обработки перебора.
 */
public class PlayerTest {

    /**
     * Проверяет, что карта добавляется в руку игрока и корректно рассчитываются очки.
     */
    @Test
    public void testAddCard() {
        Player player = new Player("Игрок");
        Card card = new Card("8", "Червы");
        player.receiveCard(card);
        
        assertEquals(1, player.getHand().size());
        
        assertEquals(8, player.getScore());
    }

    /**
     * Проверяет, что расчет очков учитывает туз как 11, если это не приводит к перебору.
     */
    @Test
    public void testCalculateHandValueWithAce() {
        Player player = new Player("Игрок");
        player.receiveCard(new Card("A", "Пики"));
        player.receiveCard(new Card("8", "Червы"));
        
        assertEquals(19, player.getScore());
    }

    /**
     * Проверяет, что расчет очков корректно изменяет туз на 1, если это необходимо,
     * чтобы избежать перебора (сумма очков больше 21).
     */
    @Test
    public void testCalculateHandValueWithAceExceeding21() {
        Player player = new Player("Игрок");
        player.receiveCard(new Card("A", "Пики"));
        player.receiveCard(new Card("8", "Червы"));
        player.receiveCard(new Card("5", "Трефы"));
        
        assertEquals(14, player.getScore());
    }
}
