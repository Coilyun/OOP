package org.example;

public class Card {
    private final String rank;
    private final String suit;
    private final int value;

    // Конструктор карты
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = calculateValue(rank); // Рассчитываем значение карты
    }

    // Метод для вычисления значения карты
    private int calculateValue(String rank) {
        if (rank.equals("A")) {
            return 11;  // Туз
        } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
            return 10;  // Валет, дама, король
        } else {
            // Числовые карты от 1 до 10
            return Integer.parseInt(rank);  
        }
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
