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
        switch (rank) {
            case "A":
                return 11;  // Туз
            case "K":
            case "Q":
            case "J":
                return 10;  // Валет, дама, король
            default:
                try {
                    return Integer.parseInt(rank);  // Числовые карты
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Неверный ранг карты: " + rank, e);
                }
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
