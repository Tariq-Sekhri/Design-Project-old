import java.util.ArrayList;

public class GameMaster {
	static ArrayList<Card> deck = new ArrayList<Card>();
	private int numberOfPlayers = 0;
	ArrayList<Player> players = new ArrayList<Player>();
	private Player whoseTurn;

	public GameMaster(int numberOfPlayers, String[] playerNames) {
		this.numberOfPlayers = numberOfPlayers;
		for (String player : playerNames) {
			createPlayer(player);
		}
		newDeck();
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

	public static ArrayList<Card> takeFromDeck(int amount){
		ArrayList<Card> cardsToRemove = new ArrayList<Card>();
		for (int i = 0; i < amount; i++) {
			int cardToRemove = (int)(Math.random()*deck.size());
            cardsToRemove.add(deck.get(cardToRemove));
			deck.remove(cardToRemove);
        }
		return cardsToRemove;
	}
}