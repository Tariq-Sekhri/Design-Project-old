import java.util.ArrayList;
public class Deck {
    // a Deck object will represent a deck of playing cards used in go fish
    private ArrayList<Card> deck = new ArrayList<Card>();
    Deck(){
        deck = newDeck();
    }
    public ArrayList<Card> newDeck(){
        // suit
		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				deck.add(new Card(value, suit));
			}
		}
		return shuffleDeck(deck);
    }//end of newDeck

    private ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {
		for (int i = 0; i < deck.size(); i++) {
			Card tempCard = deck.remove(i);
			deck.add((int) (Math.random()) * 52, tempCard);
		}
		return deck;
	}// end of shuffleDeck

    public ArrayList<Card> takeCardsFromDeck(int amount) {
		ArrayList<Card> cardsToRemove = new ArrayList<Card>();
		for (int i = 0; i < amount && !deck.isEmpty(); i++) {
			int cardToRemove = (int) (Math.random() * deck.size());
			cardsToRemove.add(deck.get(cardToRemove));
			deck.remove(cardToRemove);
		}
		return cardsToRemove;
	}// end of takeFromCardsDeck

    public int getDeckSize() {
        return deck.size();
    }//end of getDeckSize
}
