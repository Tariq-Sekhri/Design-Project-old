public class Card {
	// Card holds information about the card
	// also hold methods for the cards
	private CardSuit suit;
	private CardValue value;

	Card(CardValue value, CardSuit suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValueInt() {
		return value.getCardValue();
	}

	public CardValue getValue() {
		return value;
	}

	public void setValue(CardValue value) {
		this.value = value;
	}

	public String getSuit() {
		return suit.getSuit();
	}

	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}

	public String toString() {
		return getValue() + " of " + getSuit();
	}
}