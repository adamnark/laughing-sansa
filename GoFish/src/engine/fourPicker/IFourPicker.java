/*
 */
package engine.fourPicker;

import engine.Card;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public interface IFourPicker {

    public LinkedList<Card> pickFour(LinkedList<Card> hand);
}
