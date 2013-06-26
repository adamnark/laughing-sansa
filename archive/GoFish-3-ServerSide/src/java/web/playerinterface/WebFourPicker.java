/*
 */
package web.playerinterface;

import engine.cards.Card;
import engine.players.Hand;
import engine.players.IFourPicker;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author adam
 */
public class WebFourPicker implements IFourPicker {

    private Collection<Card> cardsToThrow = new LinkedList<>();

    public WebFourPicker() {
    }
    
    public void setCardsToThrow(Collection<Card> cardsToThrow) {
        this.cardsToThrow.clear();
        this.cardsToThrow.addAll(cardsToThrow);
    }

    @Override
    public Collection<Card> pickFour(Hand hand) {
        return cardsToThrow;
    }
}
