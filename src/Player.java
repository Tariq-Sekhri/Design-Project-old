import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int numberOfBooks = 0;
	private ArrayList<Card> books = new ArrayList<Card>();

	Player(String name) {
		this.name = name;
		hand = GameMaster.takeFromCardsDeck(5);
	}// end of player

	public ArrayList<Card> getHand() {
		return this.hand;
	}// end of getHand

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}// end of setHand

	public String getName() {
		return name;
	}// end of getName

	public void setName(String name) {
		this.name = name;
	}// end of setName

	public int getNumberOfBooks() {
		return numberOfBooks;
	}// end of getNumberOfBooks

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}// end of setNumberOfBooks

	public ArrayList<Card> TakeFromHand() {
		ArrayList<Card> cardsToTake = new ArrayList<Card>();

		return cardsToTake;
	}// end of takeFromHand

	public void addToHand(ArrayList<Card> cardsToAdd) {
		for (Card card : cardsToAdd) {
			hand.add(card);
		}
	}// end of addToHand

	public ArrayList<Card> getBooks() {
		return books;
	}// end of getBooks

	public void addToBooks(ArrayList<Card> bookToAdd) {

	}// end of addToBooks

}