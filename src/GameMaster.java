import java.util.ArrayList;

public class GameMaster {
	/*
	 * todo
	 * naman:
	 * if player has a book remove it form the hand and add it to the books
	 * ArrayList
	 * If player gets it correct
	 * 		Take from card from hand of player who got asked
	 * 		Add to hand of player who asked
	 * 
	 * 
	 * Sehaj:
	 * If player does not get it correct
	 * they draw 1 card from the deck using takeFromCardDeck and addToHand
	 * 
	 * Check if the card they draw is card they asked for
	 * if it is	they get to go again
	 *
	 */
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
	}// end of GameMaster

	private void newDeck() {
		deck.clear();
		deck.add(new Card(CardValue.TWO, CardSuit.clubs));
		// suit
		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				deck.add(new Card(value, suit));
			}
		}
	}// end of newDeck

	private void createPlayer(String playerName) {
		players.add(new Player(playerName));
	}// end of getPlayer

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}// end of getNumberOfPlayers

	public String getWhoseTurnAsName() {
		return whoseTurn.getName();
	}// end of getWhoseTurnAsName

	public Player getWhoseTurn() {
		return whoseTurn;
	}// end of getWhoseTurn

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
			if (player.getName() != getWhoseTurnAsName()) {
				playerNames += whatPlayer + ": " + player.getName() + " \n";

			}
			whatPlayer++;
		}

		return playerNames;
	}// end of getPlayerNames

	public Boolean isPlayerGuessCorrect(String playerPick, String valuePick) {
		int valuePicked = cardValuePickToInt(valuePick);

		for (Player player : players) {
			if (player.getName().equals(playerPick)) {
				for (Card cardInHand : player.getHand()) {
					if (cardInHand.getValue() == (valuePicked)) {
						// player.TakeFromHand(cardInHand);
						// player.AddHand(cardInHand);
						return true;
					}
				}
			}
		}
		return false;
	}// end of isPlayerGuessCorrect

	public String printCardValues() {
		String output = "";
		int i = 2;
		for (CardValue value : CardValue.values()) {

			output += i + ": " + value.toString() + "\n";
			i++;
		}
		return output;
	}// end of cardValues

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

	}// end of cardValuePickToInt

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

	}// end of nextTurn

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
	}// end of isGameOver

}