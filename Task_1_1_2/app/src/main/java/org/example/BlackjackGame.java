package org.example;

import java.util.Scanner;
    


public class BlackjackGame {
    private final Deck deck; // Колода карт
    private final Player player; //  Игрок
    private final Dealer dealer; //  Дилер
    private int round;  //  Номер раунда
    private int playerScore; //  Счет игрока итоговый
    private int dealerScore; //  Счет Дилера итоговый

     //  Конструктор игры, где задаем имя игрока
    public BlackjackGame(String playerName) {
        this.deck = new Deck(); //  Инициализируем колоду
        this.player = new Player(playerName); //  Инициализируем игрока
        this.dealer = new Dealer(); //  Инициализируем дилера
        this.round = 1; //  Начинаем с 1 раунда
        this.playerScore = 0; //  Начальный счет игрока
        this.dealerScore = 0; //  Начальный счет дилера
    }

     //  Если не задали имя игрока
    public BlackjackGame() {
        this("Игрок");
    }

    // Методы для получения очков игрока и дилера
    public int getPlayerScore() {
        return player.getScore();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }
    // Методы получения игрока и диллера 
    public Player getPlayer() {
        return player;
    }
     
    public Dealer getDealer() {
        return dealer;
    }
    //  Получаем счеты по итогам игры
    public int getPlayerGameScore() {
        return playerScore;
    }

    public int getDealerGameScore() {
        return dealerScore;
    }

    // Начало игры
    public void playRound() {
        System.out.println("Раунд " + round);
        startRound();
        playerTurn();
        dealerTurn();
        determineWinner(); //  Определяем победителя раунда
        round++;
    }

    // Раздача карт
    private void startRound() {
         //  Рисуем по 2 карты игроку и дилеру
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
        Scanner scanner = new Scanner(System.in); //  Объект для чтения ввода 
        try {
            while (true) {
                System.out.println("Ваш ход");
                System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться");
                int choice = scanner.nextInt(); //  Сканируем вводимое пользователем
                if (choice == 1) {
                    player.receiveCard(deck.drawCard()); //  Берем карту
                    System.out.println("Вы открыли карту: " + player.getHand().get(player.getHand().size() - 1));
                    System.out.println("Ваши карты: " + player.getHand() + " > " + player.getScore());
                    if (player.getScore() > 21) { //  Проверка на проигрыш
                        System.out.println("Вы перебрали! Ваши очки: " + player.getScore());
                        break; //  Выходим, если перебрали карт
                    }
                } else if (choice == 0) {
                    break; //  Если игрок останавливается
                }
            }
        } finally {
            scanner.close(); //  Закрываем 
        }
    }

    // Ход дилера
    private void dealerTurn() {
        System.out.println("Ход дилера");
        dealer.receiveCard(deck.drawCard());
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
        dealer.play(deck); //  Берет карты пока не дойдет до 17 и больше
         //  Печатаем карты диллера
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
    }

    // Определение победителя
    public void determineWinner() {
        if (player.getScore() > 21) {
            System.out.println("Вы проиграли!");
            playerScore = 0;
            dealerScore = 1;
        } else if (dealer.getScore() > 21) {
            System.out.println("Дилер перебрал, вы выиграли!");
            playerScore = 1;
            dealerScore = 0;
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println("Вы выиграли!");
            playerScore = 1;
            dealerScore = 0;
        } else if (player.getScore() < dealer.getScore()) {
            System.out.println("Вы проиграли!");
            playerScore = 0;
            dealerScore = 1;
        } else {
            System.out.println("Ничья!");
            playerScore = 0;
            dealerScore = 0;
        }
    }
}
