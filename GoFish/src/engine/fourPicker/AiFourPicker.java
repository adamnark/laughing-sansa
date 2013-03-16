package engine.fourPicker;

import engine.Card;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author adamnark
 */
public class AiFourPicker implements IFourPicker {
    
    private HashMap<String, HashSet<Card>> map;
    private LinkedList<Card> hand;
    
    @Override
    public LinkedList<Card> pickFour(LinkedList<Card> hand) {
        this.hand = hand;
        this.initMap();
        this.populateMap();
        LinkedList<Card> four = this.findFour();
        
        return four;
    }
    
    private void initMap() {
        for (Card card : this.hand) {
            for (String face : card.getFaces()) {
                if (this.map.get(face) == null) {
                    this.map.put(face, new HashSet<Card>());
                }
            }
        }
    }
    
    private void populateMap() {
        for (Card card : this.hand) {
            for (String face : card.getFaces()) {
                this.map.get(face).add(card);
            }
        }
    }
    
    private LinkedList<Card> findFour() {
        LinkedList<Card> four = new LinkedList<>();
        LinkedList<Card> cardsWithCommonFaces = new LinkedList<>();
        
        for (Map.Entry<String, HashSet<Card>> entry : this.map.entrySet()) {
            if (entry.getValue().size() >= 4) {
                cardsWithCommonFaces.addAll(entry.getValue());
                break;
            }
        }

        for (int i = 0; i < 4; i++) {
            Card c = cardsWithCommonFaces.pop();
            four.add(c);
        }
        
        return four;
    }
}
