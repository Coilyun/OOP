package org.example;

public class Card {
    private final String rank; //  Номинал карты
    private final String suit; //  масть
    private final int value;  //  Стоимость карты

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
    // Получаем номинал  
    public String getRank() {
        return rank;
    }
    //  Получаем масть
    public String getSuit() {
        return suit;
    }
    //  Получаем стоимость
    public int getValue() {
        return value;
    }
   
    // Переписываем 
    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
