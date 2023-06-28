public enum CardSuit {
	// holds all the possible suits for the card
	// and methods for getting information
	hearts("Hearts"),
	spades("Spades"),
	diamonds("Diamond"),
	clubs("Club");

	private final String suit;

	private CardSuit(String name) {
		this.suit = name;
	}

	public String getSuit() {
		return suit;
	}

}