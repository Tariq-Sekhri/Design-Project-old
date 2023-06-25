public enum CardSuit {
	hearts("Hearts"),
	spades("Spades"),
	diamonds("Diamond"),
	clubs("Club");
	private  final String suit;
	private CardSuit(String name) {
        this.suit = name;
    }
	public String getSuit() {
		return suit;
	}

}