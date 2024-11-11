package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private int currentCardIndex;

    public Deck() {
        this.cards = new ArrayList<>();
        this.currentCardIndex = 0;
        initializeDeck();
    }

    // Инициализация колоды
    private void initializeDeck() {
        String[] suits = {"Пики", "Червы", "Бубны", "Трефы"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"};
        
        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(value, suit));
            }
        }
        shuffleDeck();
    }

    // Перемешивание колоды
    public void shuffleDeck() {
        Collections.shuffle(cards);
        currentCardIndex = 0;  // Сбрасываем индекс после перемешивания
    }

    // Вытаскивание карты из колоды
    public Card drawCard() {
        if (currentCardIndex < cards.size()) {
            return cards.get(currentCardIndex++);
        } else {
            return null; // Если карты закончились
        }
    }

    // Метод для получения количества оставшихся карт
    public int remainingCards() {
        return cards.size() - currentCardIndex;
    }

    // Получить всю колоду карт (например, для тестирования или отладки)
    public List<Card> getCards() {
        return cards;
    }
}

