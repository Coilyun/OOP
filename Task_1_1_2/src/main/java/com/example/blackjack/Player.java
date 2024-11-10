import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private boolean isDealer;

    public Player(boolean isDealer) {
        this.isDealer = isDealer;
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.getRank().equals("Туз")) aceCount++;
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void clearHand() {
        hand.clear();
    }

    @Override
    public String toString() {
        return hand.toString() + " > " + calculateHandValue();
    }
}
