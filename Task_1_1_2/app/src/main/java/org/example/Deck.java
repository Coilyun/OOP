package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, представляющий колоду карт для игры в Blackjack.
 * Колода инициализируется стандартными мастями и номиналами карт.
 */
public class Deck {
    private List<Card> cards; // Колода карт

    /**
     * Конструктор колоды. Создает и инициализирует колоду из 52 карт и тасует их.
     */
    public Deck() {
        cards = new LinkedList<>();
        
        // Масти и номиналы для создания стандартной колоды
        String[] suits = {"Пики", "Червы", "Трефы", "Бубны"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        // Создаем карты для каждой масти и номинала
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffleDeck(); // Перетасовываем колоду при создании
    }

    /**
     * Перетасовывает колоду случайным образом.
     */
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /**
     * Извлекает верхнюю карту из колоды. Если колода пуста, возвращает null.
     *
     * @return карта из колоды или null, если карты закончились
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Берем верхнюю карту из колоды
        }
        return null;
    }

    /**
     * Возвращает список текущих карт в колоде.
     *
     * @return список карт в колоде
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Возвращает количество оставшихся карт в колоде.
     *
     * @return количество карт в колоде
     */
    public int remainingCards() {
        return cards.size();
    }
}
