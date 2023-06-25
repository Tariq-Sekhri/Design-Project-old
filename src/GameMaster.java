import java.util.ArrayList;

public class GameMaster {
	ArrayList<Card> deck = new ArrayList<Card>();
	private int numberOfPlayers = 0;
	ArrayList<Player> players = new ArrayList<Player>();
	private Player whoseTurn;

	public void newGame(int numberOfPlayers, String[] playerNames) {
		this.numberOfPlayers = numberOfPlayers;
		for (String player : playerNames) {
			createPlayer(player);
		}
		newDeck();
		whoseTurn = players.get(0);
	}// end of newGame

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

	public void createPlayer(String playerName) {
		players.add(new Player(playerName));
	}// end of getPlayer

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public Player getTheirTurn() {
		return whoseTurn;
	}
}