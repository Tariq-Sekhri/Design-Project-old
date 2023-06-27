import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int numberOfBooks = 0;
	private ArrayList<Card> books = new ArrayList<Card>();

	Player(String name) {
		this.name = name;
		hand = GameMaster.takeFromCardsDeck(5);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public ArrayList<Card> TakeFromHand() {
		ArrayList<Card> cardsToTake = new ArrayList<Card>();

		return cardsToTake;
	}

	public void addToHand(ArrayList<Card> drawnCards) {
		for (Card card : drawnCards) {
			hand.add(card);
		}
	}

	public ArrayList<Card> getBooks() {
		return books;
	}

	public void addToBooks(ArrayList<Card> bookToAdd) {

	}

	public void takeFromHand(Card matchingCard) {

	}
}
