package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> hand;  // Рука дилера
    int score;

    public Dealer() {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    // Метод для получения карт дилера
    public List<Card> getHand() {
        return hand;
    }

    // Метод для получения очков дилера
    public int getScore() {
        int total = 0;
        int aceCount = 0;

        // Проходим по всем картам в руке дилера
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

    // Метод для получения карты
    public void receiveCard(Card card) {
        hand.add(card);  // Добавляем карту в руку дилера
    }

    // Метод для сброса руки дилера
    public void reset() {
        hand.clear();
        score = 0;
    }

    // Метод для игры дилера (брать карты до тех пор, пока у него не будет 17 или больше очков)
    public void play(Deck deck) {
        while (getScore() < 17) {
            receiveCard(deck.drawCard());  // Брать карты, пока очки меньше 17
        }
    }

    @Override
    public String toString() {
        return "Dealer";
    }
}
