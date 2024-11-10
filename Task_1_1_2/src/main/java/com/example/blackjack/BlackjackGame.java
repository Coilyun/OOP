import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Player player;
    private Player dealer;
    private int playerScore = 0;
    private int dealerScore = 0;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player(false);
        dealer = new Player(true);
    }

    public void playGame() {
        System.out.println("Добро пожаловать в Блэкджек!");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                playRound(scanner);

                System.out.println("Хотите сыграть еще один раунд? (да/нет)");
                if (!scanner.nextLine().equalsIgnoreCase("да")) break;
            }
        }

        System.out.printf("Финальный счет: Вы %d - %d Дилер%n", playerScore, dealerScore);
    }

    private void playRound(Scanner scanner) {
        deck = new Deck();
        player.clearHand();
        dealer.clearHand();

        System.out.println("\nНовый раунд начался!");

        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        System.out.println("Ваши карты: " + player);
        System.out.println("Карты дилера: [" + dealer.getHand().get(0) + ", <закрытая карта>]");

        if (player.calculateHandValue() == 21) {
            System.out.println("Поздравляем! У вас Блэкджек! Вы победили в этом раунде.");
            playerScore++;
            return;
        }

        playerTurn(scanner);
        if (player.calculateHandValue() <= 21) {
            dealerTurn();
        }
        determineWinner();
    }

    private void playerTurn(Scanner scanner) {
        while (true) {
            System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться.");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                player.addCard(deck.drawCard());
                System.out.println("Ваши карты: " + player);
                if (player.calculateHandValue() > 21) {
                    System.out.println("Вы перебрали! Дилер выиграл этот раунд.");
                    dealerScore++;
                    return;
                }
            } else if (choice.equals("0")) {
                break;
            }
        }
    }

    private void dealerTurn() {
        System.out.println("Ход дилера...");
        System.out.println("Карты дилера: " + dealer);

        while (dealer.calculateHandValue() < 17) {
            Card card = deck.drawCard();
            dealer.addCard(card);
            System.out.println("Дилер берет карту: " + card);
        }

        System.out.println("Карты дилера: " + dealer);
    }

    private void determineWinner() {
        int playerValue = player.calculateHandValue();
        int dealerValue = dealer.calculateHandValue();

        if (dealerValue > 21 || (playerValue <= 21 && playerValue > dealerValue)) {
            System.out.println("Вы выиграли этот раунд!");
            playerScore++;
        } else if (playerValue == dealerValue) {
            System.out.println("Ничья!");
        } else {
            System.out.println("Дилер выиграл этот раунд.");
            dealerScore++;
        }
        System.out.printf("Счет: Вы %d - %d Дилер%n", playerScore, dealerScore);
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.playGame();
    }
}
