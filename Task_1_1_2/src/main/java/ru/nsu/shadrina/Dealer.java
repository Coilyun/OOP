package ru.nsu.shadrina;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий дилера в игре Blackjack.
 * Дилер имеет руку карт и логику игры для набора очков.
 */
public class Dealer {
    private List<Card> hand; // Рука дилера
    int score;       // Очки дилера

    /**
     * Конструктор для создания дилера с пустой рукой и нулевыми очками.
     */
    public Dealer() {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Возвращает карты дилера.
     *
     * @return список карт в руке дилера
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Рассчитывает и возвращает текущие очки дилера. 
     * Если сумма очков больше 21 и в руке есть тузы, 
     * то каждый туз учитывается как 1 очко вместо 11.
     *
     * @return общее количество очков дилера
     */
    public int getScore() {
        int total = 0;
        int aceCount = 0;

        // Проходим по всем картам в руке дилера и суммируем очки
        for (Card card : hand) {
            total += card.getValue();
            if ("A".equals(card.getRank())) {
                aceCount++;
            }
        }

        // Преобразуем туз в 1, если сумма очков превышает 21
        while (total > 21 && aceCount > 0) {
            total -= 10; // Меняем значение туза с 11 на 1
            aceCount--;
        }

        return total;
    }

    /**
     * Добавляет карту в руку дилера.
     *
     * @param card карта, которую дилер получает
     */
    public void receiveCard(Card card) {
        hand.add(card); // Добавляем карту в руку дилера
    }

    /**
     * Сбрасывает руку дилера, очищая карты и сбрасывая очки.
     */
    public void reset() {
        hand.clear();
        score = 0;
    }

    /**
     * Игровой процесс дилера. Дилер берет карты до тех пор,
     * пока сумма очков не станет равна или больше 17.
     *
     * @param deck колода, из которой дилер берет карты
     */
    public void play(Deck deck) {
        while (getScore() < 17) {
            receiveCard(deck.drawCard()); // Дилер берет карты, пока очки меньше 17
        }
    }

    /**
     * Переопределяет строковое представление дилера.
     *
     * @return строковое представление дилера
     */
    @Override
    public String toString() {
        return "Dealer";
    }
}
