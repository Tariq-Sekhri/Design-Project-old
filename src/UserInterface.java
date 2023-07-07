import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // user interface is what the players will use to play the game.
    // it will call methods from game master for the game logic
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = false;
        do {
            /* starting a new game */
            welcome();
            int numberOfPlayer = getNumberOfPlayers();
            String[] PlayerNames = getPlayerNames(numberOfPlayer);
            GameMaster game = new GameMaster(PlayerNames);
            do {
                String playerPick = playerPick(game);
                String valuePick = valuePick(game).toUpperCase();
                boolean isCorrect = game.isPlayerGuessCorrect(playerPick, valuePick);
                if (!isCorrect) {
                    System.out.println("You got it wrong! next player turn");
                    game.nextTurn();
                } else {
                    System.out.println("you got it right! so you can go again!");
                }
            } while (!game.isGameOver());// emd of game
            System.out.println("would you like to play again? enter yes if so");
            playAgain = doTheyWantToPlayerAgain(userInput.nextLine());
        } while (playAgain);
    }// end of main

    private static String[] getPlayerNames(int numberOfPlayer) {
        String[] PlayerNames = new String[numberOfPlayer];
        System.out.println("please enter the name of the first player");
        PlayerNames[0] = userInput.nextLine();
        for (int i = 1; i < numberOfPlayer; i++) {
            System.out.println("please enter the name of player " + (i + 1));
            PlayerNames[i] = userInput.nextLine();
        }
        return PlayerNames;
    }// end of getPlayerNames

    private static String valuePick(GameMaster game) {
        
        System.out.println("please enter the value of the card you would like");
        for (String card : game.getWhoseTurn().handToString()) {
            System.out.println(card);
        }
        return userInput.nextLine();
    }// end of getPlayerNames

    private static String playerPick(GameMaster game) {
        System.out.println("its " + game.getWhoseTurnAsName() + " turn");
        System.out.println("please enter the name of the player you would like to ask");
        System.out.println("or the number of the player you would like to");
        System.out.println(game.getPlayerNames() + "*************");
        String playerPick = userInput.nextLine();
        try {
            int playerPicked = Integer.parseInt(playerPick);
            ArrayList<Player> players = game.getPlayers();
            return players.get(playerPicked - 1).getName();

        } catch (Exception e) {
            return playerPick;
        }

    }// end of playerPick

    private static void welcome() {
        String rules = "nothing yet";
        System.out.println("hello and welcome to go fish");
        System.out.println("here are the rules" + rules);
        System.out.println("please enter the number of players");
    }// end of welcome

    private static int getNumberOfPlayers() {
        boolean anyErrors = false;
        do {
            try {
                int playerAmount = Integer.parseInt(userInput.nextLine());
                if (playerAmount > 6) {
                    System.out.println("their is a max of 10 players please enter a number less than 10");
                    anyErrors = true;
                } else if (playerAmount > 0) {
                    return playerAmount;
                } else {
                    anyErrors = true;
                    System.out.println("please enter a number greater than zero");
                }
            } catch (Exception e) {
                System.out.println("please enter a number");
                anyErrors = true;
            }
        } while (anyErrors);
        return -100;
    }// end of getNumberOfPlayers

    private static boolean doTheyWantToPlayerAgain(String playAgain) {
        if (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("ya")
                || playAgain.equalsIgnoreCase("yep") || playAgain.equalsIgnoreCase("1")) {
            return true;
        }
        return false;
    }// end of doTheyWantToPlayerAgain
}// end of UserInterface