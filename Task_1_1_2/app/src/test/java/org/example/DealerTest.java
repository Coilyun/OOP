package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    @Test
    public void testDealerPlay() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        
        int initialScore = dealer.getScore();
        dealer.play(deck);
        
        // Дилер должен продолжать брать карты, пока сумма его очков меньше 17
        assertTrue(dealer.getScore() >= 17, "Дилер должен иметь хотя бы 17 очков");
        assertTrue(dealer.getScore() > initialScore, "Дилер должен улучшить свой счет");
    }
}
