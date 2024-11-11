package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;  // Рука игрока (список карт)
    private int score;

    // Конструктор
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    // Метод для получения имени игрока
    public String getName() {
        return name;
    }

    // Метод для получения карт игрока
    public List<Card> getHand() {
        return hand;
    }

    // Метод для получения очков игрока
    public int getScore() {
        int total = 0;
        int aceCount = 0;

        // Проходим по всем картам в руке игрока
        for (Card card : hand) {
            total += card.getValue();
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }

        // Преобразуем туз в 1, если сумма очков превышает 21
        while (total > 21 && aceCount > 0) {
            total -= 10; // Меняем туз с 11 на 1
            aceCount--;
        }

        return total;
    }

    // Метод для добавления карты в руку игрока
    public void receiveCard(Card card) {
        hand.add(card);  // Добавляем карту в руку
    }

    // Метод для сброса руки игрока (например, для начала нового раунда)
    public void reset() {
        hand.clear();
        score = 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
