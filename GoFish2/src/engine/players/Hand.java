package engine.players;

import engine.cards.Card;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author adamnark
 */
public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCardsToHand(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeCardFromHand(Card card) {
        boolean hasRemoved = this.cards.remove(card);
        if (!hasRemoved) {
            throw new NoSuchElementException();
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
