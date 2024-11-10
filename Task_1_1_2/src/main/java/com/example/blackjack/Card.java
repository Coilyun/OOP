public class Card {
    private String suit;  // Масть карты (Червы, Пики, Трефы, Бубны)
    private String rank;  // Название карты (2-10, Валет, Дама, Король, Туз)
    private int value;    // Значение карты для игры

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " " + suit + " (" + value + ")";
    }
}
