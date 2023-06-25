import java.util.ArrayList;

public class GameMaster {
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private int numberOfPlayers = 0;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player whoseTurn;
    private int whoseTurnNumber = 0;

    public GameMaster(int numberOfPlayers, String[] playerNames) {
        this.numberOfPlayers = numberOfPlayers;
        newDeck();
        for (String player : playerNames) {
            createPlayer(player);
        }
        whoseTurn = players.get(0);
    }

    private void newDeck() {
        deck.clear();
        deck.add(new Card(CardValue.TWO, CardSuit.clubs));
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                deck.add(new Card(value, suit));
            }
        }
    }

    private void createPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getWhoseTurnAsName() {
        return whoseTurn.getName();
    }

    public Player getWhoseTurn() {
        return whoseTurn;
    }

    public static ArrayList<Card> takeFromCardsDeck(int amount) {
        ArrayList<Card> cardsToRemove = new ArrayList<Card>();
        for (int i = 0; i < amount; i++) {
            int cardToRemove = (int) (Math.random() * deck.size());
            cardsToRemove.add(deck.get(cardToRemove));
            deck.remove(cardToRemove);
        }
        return cardsToRemove;
    }

    public String getPlayerNames() {
        String playerNames = "";
        int whatPlayer = 1;
        for (Player player : players) {
            if (!player.getName().equals(getWhoseTurnAsName())) {
                playerNames += whatPlayer + ": " + player.getName() + " \n";
            }
            whatPlayer++;
        }
        return playerNames;
    }

    public Boolean isPlayerGuessCorrect(String playerPick, String valuePick) {
        int valuePicked = cardValuePickToInt(valuePick);
        Player playerAsked = null;

        for (Player player : players) {
            if (player.getName().equals(playerPick)) {
                playerAsked = player;
                break;
            }
        }

        if (playerAsked != null) {
            boolean foundMatch = false;
            ArrayList<Card> matchingCards = new ArrayList<>();

            for (Card cardInHand : playerAsked.getHand()) {
                if (cardInHand.getValue() == valuePicked) {
                    matchingCards.add(cardInHand);
                    foundMatch = true;
                }
            }

            if (foundMatch) {
                for (Card matchingCard : matchingCards) {
                    playerAsked.takeFromHand(matchingCard);
                    whoseTurn.addToHand(matchingCard);
                }
                return true;
            } else {
                ArrayList<Card> drawnCards = takeFromCardsDeck(1);
                whoseTurn.addToHand(drawnCards);

                for (Card drawnCard : drawnCards) {
                    if (drawnCard.getValue() == valuePicked) {
                        nextTurn();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String printCardValues() {
        String output = "";
        int i = 2;
        for (CardValue value : CardValue.values()) {
            output += i + ": " + value.toString() + "\n";
            i++;
        }
        return output;
    }

    private int cardValuePickToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            switch (value) {
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void nextTurn() {
        if (whoseTurnNumber < players.size() - 1) {
            whoseTurnNumber++;
            whoseTurn = players.get(whoseTurnNumber);
        } else {
            whoseTurnNumber = 0;
            whoseTurn = players.get(whoseTurnNumber);
        }
    }

    public boolean isGameOver() {
        int totalBooks = 0;
        for (Player book : players) {
            totalBooks += book.getNumberOfBooks();
        }
        if (deck.size() == 0 && totalBooks == 13) {
            return true;
        } else {
            return false;
        }
    }
}
