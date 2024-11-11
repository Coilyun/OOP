package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        // Инициализация колоды
        String[] suits = {"Пики", "Червы", "Трефы", "Бубны"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        // Создаем колоду
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffleDeck();  // Перетасовываем колоду
    }

    // Метод для тасования колоды
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    // Метод для извлечения карты из колоды
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);  // Берем верхнюю карту из колоды
        }
        return null;  // Если карты закончились, возвращаем null
    }

    // Метод для получения текущих карт в колоде
    public List<Card> getCards() {
        return cards;  // Возвращаем список карт
    }

    public int remainingCards() {
        return cards.size();
    }
}
