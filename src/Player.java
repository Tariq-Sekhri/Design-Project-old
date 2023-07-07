import java.util.ArrayList;

public class Player {
	// Player holds information about the Player
	// also hold methods for the Player
	private String name;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int numberOfBooks = 0;
	private ArrayList<Card> books = new ArrayList<Card>();

	Player(String name) {
		this.name = name;
		hand = GameMaster.takeCardsFromDeck(5);
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

	public ArrayList<Card> TakeFromHand(ArrayList<Card> cardsToTake) {
		for (Card cardToRemove : cardsToTake) {
			hand.remove(cardToRemove);
		}

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

	public void calculateBooks() {
		ArrayList<Card> whatToAddToBooks = new ArrayList<Card>();
		for (int card1Index = 0; card1Index < hand.size(); card1Index++) {
			boolean skip = false;
			for (Card waitToBeAddedToBooks : whatToAddToBooks) {
				if (hand.get(card1Index).getValueInt() == waitToBeAddedToBooks.getValueInt()) {
					skip = true;
				}
			}
			if (skip) {
				continue;
			}
			for (int card2Index = card1Index + 1; card2Index < hand.size(); card2Index++) {
				if (hand.get(card2Index).getValueInt() == hand.get(card1Index).getValueInt()) {
					for (int card3Index = card2Index + 1; card3Index < hand.size(); card3Index++) {
						if (hand.get(card3Index).getValueInt() == hand.get(card1Index).getValueInt()) {
							for (int card4Index = card3Index + 1; card4Index < hand.size(); card3Index++) {
								if (hand.get(card4Index).getValueInt() == hand.get(card1Index).getValueInt()) {
									whatToAddToBooks.add(hand.get(card1Index));
									whatToAddToBooks.add(hand.get(card2Index));
									whatToAddToBooks.add(hand.get(card3Index));
									whatToAddToBooks.add(hand.get(card4Index));
								}

							}
						}
					}
				}
			}
		}
		if (!whatToAddToBooks.isEmpty()) {
			TakeFromHand(whatToAddToBooks);
			addToBooks(whatToAddToBooks);
		}
	}// end of calculateBooks

	public void addToBooks(ArrayList<Card> cardsToAdd) {
		for (Card card : cardsToAdd) {
			books.add(card);
		}
	}// end of addToBooks

	public String[] handToString() {
		String[] output = new String[hand.size()];
		for (int i = 0; i < hand.size(); i++) {
			output[i] = (i + 1) + ". " + hand.get(i).getValue();
		}
		return output;
	}
}