package ru.nsu.shadrina;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса App.
 */
class AppTest {

    @Test
    void testMainMethod() {
        // Создание объекта BlackjackGame в методе main
        BlackjackGame game = new BlackjackGame("Никита");

        // Проверка, что объект игры инициализирован с правильным именем
        assertEquals("Никита", game.getName(), "Имя игрока должно быть 'Никита'");

        // Запуск первого раунда
        game.playRound();
        
        // Проверка вывода в консоль, если метод playRound() что-то печатает

    }
}
