package engine.players.ai;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.IFourPicker;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author adamnark
 */
public class AiFourPicker implements IFourPicker {

    @Override
    public Collection<Card> pickFour(Hand hand) {
        Collections.shuffle(hand.getCards());
        HashMultimap<Series, Card> map = initMultimap(hand);

        LinkedList<Card> four = new LinkedList<>();
        for (Series series : map.keySet()) {
            int count = map.get(series).size();
            if (count >= 4) {
                four.addAll(firstFourFromSet(map.get(series)));
                break;
            }
        }

        return four.isEmpty() ? null : four;
    }

    private Collection<? extends Card> firstFourFromSet(Set<Card> cards) {
        Set<Card> firstFour = new HashSet<>();
        Iterator<Card> it = cards.iterator();
        while (firstFour.size() < 4) {
            firstFour.add(it.next());
        }

        return firstFour;
    }

    private HashMultimap<Series, Card> initMultimap(Hand hand) {
        HashMultimap<Series, Card> map = HashMultimap.create();
        for (Card cardInHand : hand.getCards()) {
            for (Series series : cardInHand.getSeries()) {
                map.put(series, cardInHand);
            }
        }
        return map;
    }
}
