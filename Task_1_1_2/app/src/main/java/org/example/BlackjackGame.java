package org.example;

import java.util.Scanner;

public class BlackjackGame {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private int round;

    // Конструктор, создающий игрока с именем
    public BlackjackGame(String playerName) {
        this.deck = new Deck();
        this.player = new Player(playerName);  // Создаем игрока с именем
        this.dealer = new Dealer();
        this.round = 1;
    }

    public BlackjackGame() {
        this("Игрок");  // Имя по умолчанию
    }
    // Публичный метод для получения счета игрока
    public int getPlayerScore() {
        return player.getScore();
    }
    // Публичный метод для получения счета дилера
    public int getDealerScore() {
        return dealer.getScore();
    }
     // Метод для получения игрока
     public Player getPlayer() {
        return player;
    }

    // Метод для получения дилера (если он также используется в тестах)
    public Dealer getDealer() {
        return dealer;
    }
    // Метод для начала игры
    public void playRound() {
        System.out.println("Раунд " + round);
        startRound();
        playerTurn();
        dealerTurn();
        determineWinner();
        round++;
    }

    // Раздача карт
    private void startRound() {
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());

        player.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());

        System.out.println("Дилер раздал карты");
        System.out.println(player.getName() + ", ваши карты: " + player.getHand() + " > " + player.getScore());
        System.out.println("Карты дилера: " + dealer.getHand().get(0) + " и <закрытая карта>");
    }

    // Ход игрока
    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Ваш ход");
                System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.receiveCard(deck.drawCard());
                    System.out.println("Вы открыли карту: " + player.getHand().get(player.getHand().size() - 1));
                    System.out.println("Ваши карты: " + player.getHand() + " > " + player.getScore());
                    if (player.getScore() > 21) {
                        System.out.println("Вы перебрали! Ваши очки: " + player.getScore());
                        break;
                    }
                } else if (choice == 0) {
                    break;
                }
            }
        } finally {
            scanner.close(); // Закрываем Scanner
        }
    }
    

    // Ход дилера
    private void dealerTurn() {
        System.out.println("Ход дилера");
        dealer.receiveCard(deck.drawCard());  // Открываем вторую карту дилера
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
        dealer.play(deck);
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
    }

    // Определение победителя
    public void determineWinner() {
        if (player.getScore() > 21) {
            System.out.println("Вы проиграли!");
        } else if (dealer.getScore() > 21) {
            System.out.println("Дилер перебрал, вы выиграли!");
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println("Вы выиграли!");
        } else if (player.getScore() < dealer.getScore()) {
            System.out.println("Вы проиграли!");
        } else {
            System.out.println("Ничья!");
        }
    }
}
