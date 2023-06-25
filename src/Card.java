public class Card {

	private CardSuit suit;
	private CardValue value;


	Card(CardValue value, CardSuit suit )  {
		this.value = value; 
		this.suit = suit;
	}
	public int getValue() {
		return value.getCardValue();
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
		return getValue() +" of "+ getSuit();
	}
}