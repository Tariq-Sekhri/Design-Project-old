import java.util.ArrayList;

public class GameMaster {

	// game master hold information about the game as well as the logic of the game;
	private Deck deck = new Deck();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player whoseTurn;
	private int whoseTurnNumber = 0;

	public GameMaster(String[] playerNames) {
		int cardsPerPlayer;
		cardsPerPlayer = (playerNames.length<4) ? 7:  5;
		for (String player : playerNames) {
			createPlayer(player,cardsPerPlayer);
		}
		whoseTurn = players.get(0);
	}// end of GameMaster

	private void createPlayer(String playerName,int cardsPerPlayer) {
		players.add(new Player(playerName,deck.takeCardsFromDeck(cardsPerPlayer)));
	}// end of getPlayer

	public int getNumberOfPlayers() {
		return players.size();
	}// end of getNumberOfPlayers

	public String getWhoseTurnAsName() {
		return whoseTurn.getName();
	}// end of getWhoseTurnAsName

	public Player getWhoseTurn() {
		return whoseTurn;
	}// end of getWhoseTurn


	public void handEmpty() {
		if (getWhoseTurn().getHand().size() <= 0) {
			getWhoseTurn().addToHand(deck.takeCardsFromDeck(5));
		}
	}// end of handEmpty

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

	public boolean isPlayerGuessCorrect(String playerPick, String valuePick) {
		int valuePicked = cardValuePickToInt(valuePick);
		Player playerAsked = whoseTurn;
		ArrayList<Card> cardsToTake = new ArrayList<Card>();
		for (Player player : players) {
			if (player.getName().equals(playerPick)) {
				for (Card cardInHand : player.getHand()) {
					if (cardInHand.getValueInt() == (valuePicked)) {
						cardsToTake.add(cardInHand);
						playerAsked = player;
					}
				}
			}
		}

		if (!cardsToTake.isEmpty() && playerAsked != whoseTurn) {
			playerAsked.TakeFromHand(cardsToTake);
			whoseTurn.addToHand(cardsToTake);
			whoseTurn.calculateBooks();
			return true;
		} else {
			ArrayList<Card> cardToAdd = deck.takeCardsFromDeck(1);
			whoseTurn.addToHand(cardToAdd);
			return valuePicked == cardToAdd.get(0).getValueInt();
		}

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
		int valueAsInt = -100;
		try {
			valueAsInt = Integer.parseInt(value);
			return whoseTurn.getHand().get(valueAsInt).getValueInt();
		} catch (Exception e) {
			String[] cardsInHand = whoseTurn.handToString();
			for (int i = 0; i < cardsInHand.length; i++) {
				if (value.equals(cardsInHand[i].substring(3))) {
					return whoseTurn.getHand().get(i).getValueInt();
				}
			}
		}
		return valueAsInt;

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
		if (deck.getDeckSize() == 0 && totalBooks == 13) {
			return true;
		} else {
			return false;
		}
	}// end of isGameOver

}