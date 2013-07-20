package engine.players;

import engine.cards.Card;
import engine.cards.Series;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author adamnark
 */
public class Hand {

    private ArrayList<Card> cards;

    @Override
    public String toString() {
        return "{" + cards + "}";
    }

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(Hand other) {
        this();
        for (Card card : other.cards) {
            this.cards.add(new Card(card));
        }
    }

    public void addCardsToHand(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCardToHand(Card card) {
        card.makeValid();
        this.cards.add(card);
    }

    public void removeCardFromHand(Card card) {
        boolean hasRemoved = this.cards.remove(card);
        if (!hasRemoved) {
            throw new NoSuchElementException();
        }
        card.makeInvalid();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isSeriesInHand(Series series) {
        for (Card card : getCards()) {
            if (card.isInSeries(series)) {
                return true;
            }
        }
        return false;
    }

    public void empty() {
        this.cards = new ArrayList<>();
    }
}
