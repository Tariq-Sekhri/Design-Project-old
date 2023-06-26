import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMaster {
    private List<Card> deck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Player whoseTurn;

    public GameMaster(String[] playerNames) {
        for (String playerName : playerNames) {
            createPlayer(playerName);
        }
        newDeck();
        whoseTurn = players.get(0);
    }

    private void newDeck() {
        deck.clear();
        deck.add(new Card(CardValue.TWO, CardSuit.CLUBS));
        for (CardSuit suit : CardSuit.values()) {
            if (suit != CardSuit.CLUBS) { // Skip adding TWO of CLUBS again
                for (CardValue value : CardValue.values()) {
                    deck.add(new Card(value, suit));
                }
            }
        }
    }

    private void createPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public String getWhoseTurnAsName() {
        return whoseTurn.getName();
    }

    public Player getWhoseTurn() {
        return whoseTurn;
    }

    public List<Card> takeFromDeck(int amount) {
        List<Card> cardsToRemove = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            int cardToRemoveIndex = random.nextInt(deck.size());
            cardsToRemove.add(deck.get(cardToRemoveIndex));
            deck.remove(cardToRemoveIndex);
        }
        return cardsToRemove;
    }

    public String getPlayerNames() {
        StringBuilder playerNames = new StringBuilder();
        int playerIndex = 1;
        for (Player player : players) {
            if (!player.equals(whoseTurn)) {
                playerNames.append(playerIndex).append(": ").append(player.getName()).append("\n");
            }
            playerIndex++;
        }
        return playerNames.toString();
    }

    public boolean isPlayerGuessCorrect(String playerPick, String valuePick) {
        int valuePicked = cardValuePickToInt(valuePick);
        Player askedPlayer = getPlayerByName(playerPick);
        Player guessingPlayer = whoseTurn;

        if (askedPlayer != null && guessingPlayer != null) {
            List<Card> askedPlayerHand = askedPlayer.getHand();
            List<Card> matchingCards = new ArrayList<>();
            for (Card cardInHand : askedPlayerHand) {
                if (cardInHand.getValue() == valuePicked) {
                    matchingCards.add(cardInHand);
                }
            }

            if (!matchingCards.isEmpty()) {
                // Remove matching cards from asked player's hand
                askedPlayerHand.removeAll(matchingCards);
                // Add matching cards to guessing player's hand
                guessingPlayer.addToHand(matchingCards);
                // Check if the player has four cards of the same value
                if (guessingPlayer.checkForBooks()) {
                    List<Card> books = guessingPlayer.getBooks();
                    guessingPlayer.removeFromHand(books);
                    guessingPlayer.addToBooks(books);
                }
                return true;
            }
        }
        return false;
    }

    private Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public String printCardValues() {
        StringBuilder output = new StringBuilder();
        int i = 2;
        for (CardValue value : CardValue.values()) {
            output.append(i).append(": ").append(value).append("\n");
            i++;
        }
        return output.toString();
    }

    private int cardValuePickToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            switch (value.toUpperCase()) {
                case "ACE":
                    return 14;
                case "KING":
                    return 13;
                case "QUEEN":
                    return 12;
                case "JACK":
                    return 11;
                default:
                    return -100;
            }
        }
    }

    public void nextTurn() {
        int currentIndex = players.indexOf(whoseTurn);
        int nextIndex = (currentIndex + 1) % players.size();
        whoseTurn = players.get(nextIndex);
    }

    public boolean isGameOver() {
        int totalBooks = players.stream().mapToInt(Player::getNumberOfBooks).sum();
        return deck.isEmpty() && totalBooks == 13;
    }
}
