package engine.players;

import engine.cards.Card;
import java.util.Collection;



/**
 *
 * @author adamnark
 */
public interface IFourPicker {
    public Collection<Card> pickFour(Hand hand);
}
