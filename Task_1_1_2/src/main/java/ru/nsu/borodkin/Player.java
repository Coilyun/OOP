package ru.nsu.borodkin;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий игрока в игре Blackjack.
 * Содержит информацию о руке игрока, его имени и текущем счете.
 */
public class Player {
    private String name;
    private List<Card> hand;
    int score;

    /**
     * Конструктор игрока.
     *
     * @param name имя игрока
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Возвращает имя игрока.
     *
     * @return имя игрока
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает текущую руку игрока.
     *
     * @return список карт в руке игрока
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Вычисляет и возвращает текущий счет игрока с учетом правила для туза.
     *
     * @return текущий счет игрока
     */
    public int getScore() {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            total += card.getValue();
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    /**
     * Добавляет карту в руку игрока.
     *
     * @param card карта для добавления в руку
     */
    public void receiveCard(Card card) {
        hand.add(card);
    }

    /**
     * Сбрасывает руку игрока и счет (используется для начала нового раунда).
     */
    public void reset() {
        hand.clear();
        score = 0;
    }

    /**
     * Возвращает имя игрока как строку.
     *
     * @return строковое представление имени игрока
     */
    @Override
    public String toString() {
        return name;
    }
}
