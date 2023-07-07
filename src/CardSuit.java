public enum CardSuit {
	// holds all the possible suits for the card
	// and methods for getting information
	HEARTS("Hearts"),
	SPADES("Spades"),
	DIAMOND("Diamond"),
	CLUBS("Club");

	private final String suit;

	private CardSuit(String suit) {
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}

}