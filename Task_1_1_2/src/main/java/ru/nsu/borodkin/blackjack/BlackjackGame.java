package ru.nsu.borodkin.blackjack;

import java.util.Scanner;

/**
 * Класс для моделирования игры Blackjack (Блэкджек) между игроком и дилером.
 */
public class BlackjackGame {
    private final Deck deck;
    private final Player player; 
    private final Dealer dealer; 
    private int round; 
    private int playerScore; 
    private int dealerScore; 

    /**
     * Конструктор игры BlackjackGame.
     *
     * @param playerName Имя игрока.
     */
    public BlackjackGame(String playerName) {
        this.deck = new Deck(); 
        this.player = new Player(playerName); 
        this.dealer = new Dealer(); 
        this.round = 1; 
        this.playerScore = 0; 
        this.dealerScore = 0; 
    }

    /**
     * Конструктор игры по умолчанию.
     * Создает игрока с именем "Игрок".
     */
    public BlackjackGame() {
        this("Игрок");
    }

    /**
     * Получить текущий счет игрока.
     *
     * @return Счет игрока.
     */
    public int getPlayerScore() {
        return player.getScore();
    }

    /**
     * Получить текущий счет дилера.
     *
     * @return Счет дилера.
     */
    public int getDealerScore() {
        return dealer.getScore();
    }

    /**
     * Получить объект игрока.
     *
     * @return Объект Player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Получить объект дилера.
     *
     * @return Объект Dealer.
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Получить итоговый счет игрока после раунда.
     *
     * @return Итоговый счет игрока.
     */
    public int getPlayerGameScore() {
        return playerScore;
    }

    /**
     * Получить итоговый счет дилера после раунда.
     *
     * @return Итоговый счет дилера.
     */
    public int getDealerGameScore() {
        return dealerScore;
    }

    /**
     * Запустить раунд игры.
     * Включает раздачу карт, ход игрока и ход дилера, а затем определяет победителя.
     */
    public void playRound() {
        System.out.println("Раунд " + round);
        startRound();
        playerTurn();
        dealerTurn();
        determineWinner();
        round++;
    }

    public String getName() {
        return player.getName();
    }    

    /**
     * Раздача карт для начала раунда.
     * Игрок и дилер получают по две карты из колоды.
     */
    void startRound() {
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        
        System.out.println("Дилер раздал карты");
        System.out.println(player.getName() + ", ваши карты: " 
            + player.getHand() + " > " + player.getScore());
        System.out.println("Карты дилера: " 
            + dealer.getHand().get(0) + " и <закрытая карта>");
    }

    /**
     * Ход игрока, где он может выбрать взять карту или остановиться.
     */
    void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Ваш ход");
                System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        player.receiveCard(deck.drawCard());

                        System.out.println("Вы открыли карту: " 
                            + player.getHand().get(player.getHand().size() - 1));
                        System.out.println("Ваши карты: " + player.getHand() 
                            + " > " + player.getScore());
                        
                        if (player.getScore() > 21) {
                            System.out.println("Вы перебрали! Ваши очки: " 
                                + player.getScore());
                            break;
                        }
                    } else if (choice == 0) {
                        break;
                    }
                } else {
                    System.out.println("Введите правильное число (1 или 0).");
                    scanner.next();
                }
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * Ход дилера, где он берет карты до достижения 17 очков или более.
     */
    void dealerTurn() {
        System.out.println("Ход дилера");
        dealer.receiveCard(deck.drawCard());
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
        
        dealer.play(deck);
        
        System.out.println("Карты дилера: " + dealer.getHand() + " > " + dealer.getScore());
    }

    /**
     * Определяет победителя раунда и обновляет итоговые очки игрока и дилера.
     */
    void determineWinner() {
        if (player.getScore() > 21 && dealer.getScore() > 21) {
            System.out.println("Ничья! Оба игрока перебрали.");
            playerScore = 0;
            dealerScore = 0;
        } else if (player.getScore() > 21) {
            System.out.println("Вы проиграли! Перебрали.");
            playerScore = 0;
            dealerScore = 1;
        } else if (dealer.isBusted()) {
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
