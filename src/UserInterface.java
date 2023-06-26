import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Go Fish!");

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] playerNames = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of Player " + (i + 1) + ": ");
            playerNames[i] = scanner.nextLine();
        }

        GameMaster gameMaster = new GameMaster(playerNames);
        gameMaster.startGame();

        while (!gameMaster.isGameOver()) {
            System.out.println();
            System.out.println("Current turn: " + gameMaster.getCurrentPlayer().getName());
            System.out.println("Your hand: " + gameMaster.getCurrentPlayer().getHand());
            System.out.print("Enter the name of the player you want to ask: ");
            String playerName = scanner.nextLine();
            System.out.print("Enter the value you want to ask for: ");
            String value = scanner.nextLine();

            gameMaster.playTurn(playerName, value);
            gameMaster.nextTurn();
        }

        System.out.println();
        System.out.println("Game over!");

        for (Player player : gameMaster.getPlayers()) {
            System.out.println(player.getName() + "'s books: " + player.getBooks());
        }
    }
}
