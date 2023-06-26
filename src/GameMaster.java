import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMaster {
    private List<Card> deck;
    private List<Player> players;
    private Player whoseTurn;

    public GameMaster(String[] playerNames) {
        players = new ArrayList<>();
        deck = new ArrayList<>();
        createPlayers(playerNames);
        initializeDeck();
        whoseTurn = players.get(0);
    }

    private void createPlayers(String[] playerNames) {
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.add(player);
        }
    }

    private void initializeDeck() {
        deck = Card.generateDeck();
        shuffleDeck();
    }

    private void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    public void startGame() {
        dealCards();
    }

    private void dealCards() {
        int numPlayers = players.size();
        int cardsPerPlayer = 7; // Number of cards to be dealt to each player
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (int j = 0; j < numPlayers; j++) {
                Player player = players.get(j);
                Card card = deck.remove(0);
                player.addToHand(card);
            }
        }
    }

    public boolean isGameOver() {
        return deck.isEmpty() && players.stream().allMatch(Player::isHandEmpty);
    }

    public void playTurn(String playerName, String value) {
        Player askedPlayer = getPlayerByName(playerName);
        Player currentPlayer = getCurrentPlayer();

        if (askedPlayer == null || currentPlayer == null) {
            System.out.println("Invalid player name or current player.");
            return;
        }

        CardValue cardValue = CardValue.fromString(value);
        if (cardValue == null) {
            System.out.println("Invalid card value.");
            return;
        }

        List<Card> matchingCards = askedPlayer.giveCardsOfValue(cardValue);
        if (!matchingCards.isEmpty()) {
            currentPlayer.addToHand(matchingCards);
            System.out.println(currentPlayer.getName() + " received " + matchingCards.size() +
                    " card(s) of value " + cardValue + " from " + askedPlayer.getName());
            if (currentPlayer.checkForBooks()) {
                List<Card> books = currentPlayer.getBooks();
                currentPlayer.addToBooks(books);
                currentPlayer.removeFromHand(books);
                System.out.println(currentPlayer.getName() + " completed a book!");
            }
        } else {
            System.out.println(askedPlayer.getName() + " does not have any cards of value " + cardValue);
            Card drawnCard = drawCard();
            currentPlayer.addToHand(drawnCard);
            if (drawnCard.getValue() == cardValue) {
                System.out.println(currentPlayer.getName() + " drew a matching card (" + cardValue + ") and gets to go again.");
                playTurn(currentPlayer.getName(), value);
            } else {
                System.out.println(currentPlayer.getName() + " drew a card: " + drawnCard);
            }
        }
    }

    private Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }

    private Player getCurrentPlayer() {
        return whoseTurn;
    }

    private Card drawCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        }
        return null;
    }

    public void nextTurn() {
        int currentPlayerIndex = players.indexOf(whoseTurn);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        whoseTurn = players.get(nextPlayerIndex);
    }
}
