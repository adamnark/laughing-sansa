package engine.players.ai;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.IFourPicker;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class AiFourPicker implements IFourPicker {

    @Override
    public Collection<Card> pickFour(Hand hand) {
        HashMultimap<Series, Card> map = HashMultimap.create();

        for (Card cardInHand : hand.getCards()) {
            for (Series series : cardInHand.getSeries()) {
                map.put(series, cardInHand);
            }
        }

        LinkedList<Card> four = new LinkedList<>();
        for (Series series : map.keySet()) {
            int count = map.get(series).size();
            if (count == 4) {
                four.addAll(map.get(series));
                break;
            }
        }

        return four.isEmpty() ? null : four;
    }
}
