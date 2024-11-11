package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class AppTest {

    @Test
    void testMainMethod() {
        // Подготовка подмены ввода
        String simulatedInput = "1\n0\n"; // Симулируем ввод: "1" - взять карту, "0" - остановиться
        InputStream originalSystemIn = System.in; // Сохраняем оригинальный поток ввода
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes())); // Подменяем поток ввода
        
        try {
            // Создание объекта BlackjackGame
            BlackjackGame game = new BlackjackGame("Никита");

            // Проверка, что объект игры инициализирован с правильным именем
            assertEquals("Никита", game.getName(), "Имя игрока должно быть 'Никита'");

            // Запуск первого раунда
            game.playRound();

            // Пример проверки: убедитесь, что после раунда очки игрока и дилера корректны
            assertTrue(game.getPlayerScore() >= 0, "Очки игрока должны быть больше или равны 0");
            assertTrue(game.getDealerScore() >= 0, "Очки дилера должны быть больше или равны 0");

        } finally {
            // Восстановление оригинального потока ввода
            System.setIn(originalSystemIn);
        }
    }
}
