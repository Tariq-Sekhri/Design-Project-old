import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int numberOfBooks = 0;

	Player(String Name){
		
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

}