package org.example;

/**
 * Класс для запуска игры Blackjack.
 * Создает экземпляр игры и инициирует один раунд.
 */
public class App {
    
    /**
     * Точка входа в приложение.
     * Создает игру с именем игрока и запускает один раунд.
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame("Никита"); // Инициализируем игру с игроком "Никита"
        game.playRound(); // Запускаем первый раунд
    }
}
