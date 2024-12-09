package ru.nsu.borodkin;

/**
 * Класс, представляющий карту в игре Blackjack.
 * Каждая карта имеет номинал (rank), масть (suit) и значение (value).
 */
public class Card {
    private final String rank;
    private final String suit;
    private final int value; 

    /**
     * Конструктор для создания новой карты с указанными номиналом и мастью.
     *
     * @param rank номинал карты
     * @param suit масть карты
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = calculateValue(rank); 
    }

    /**
     * Рассчитывает стоимость карты в очках по ее номиналу.
     * Туз (A) стоит 11 очков, король (K), дама (Q), валет (J) — 10 очков,
     * числовые карты — их числовое значение.
     *
     * @param rank номинал карты
     * @return стоимость карты
     */
    private int calculateValue(String rank) {
        if (rank.equals("A")) {
            return 11;
        } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }

    /**
     * Возвращает номинал карты.
     *
     * @return номинал карты
     */
    public String getRank() {
        return rank;
    }

    /**
     * Возвращает масть карты.
     *
     * @return масть карты
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Возвращает стоимость карты.
     *
     * @return стоимость карты
     */
    public int getValue() {
        return value;
    }

    /**
     * Возвращает строковое представление карты в формате "Номинал Масть".
     *
     * @return строковое представление карты
     */
    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
