import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private List<Card> books;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getBooks() {
        return books;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void addToHand(List<Card> cards) {
        hand.addAll(cards);
    }

    public void removeFromHand(List<Card> cards) {
        hand.removeAll(cards);
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public List<Card> giveCardsOfValue(CardValue value) {
        List<Card> matchingCards = new ArrayList<>();
        for (Card card : hand) {
            if (card.getValue() == value) {
                matchingCards.add(card);
            }
        }
        hand.removeAll(matchingCards);
        return matchingCards;
    }

    public boolean checkForBooks() {
        for (CardValue value : CardValue.values()) {
            int count = 0;
            for (Card card : hand) {
                if (card.getValue() == value) {
                    count++;
                }
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    public void addToBooks(List<Card> cards) {
        books.addAll(cards);
    }
}
