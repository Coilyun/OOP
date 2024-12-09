package ru.nsu.borodkin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import ru.nsu.borodkin.blackjack.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;


class AppTest {

    @Test
    void testMainMethod() {
        String simulatedInput = "1\n0\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        try {
            BlackjackGame game = new BlackjackGame("Никита");

            assertEquals("Никита", game.getName(), "Имя игрока должно быть 'Никита'");

            game.playRound();

            assertTrue(game.getPlayerScore() >= 0, "Очки игрока должны быть больше или равны 0");
            assertTrue(game.getDealerScore() >= 0, "Очки дилера должны быть больше или равны 0");

        } finally {
            System.setIn(originalSystemIn);
        }
    }
}
