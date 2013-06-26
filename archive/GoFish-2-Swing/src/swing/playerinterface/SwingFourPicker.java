package swing.playerinterface;

import engine.cards.Card;
import engine.players.Hand;
import engine.players.IFourPicker;
import engine.players.exceptions.InvalidFourRuntimeException;
import java.util.Collection;
import java.util.LinkedList;
import swing.components.game.card.JButtonCard;
import swing.components.game.play.playarea.JPanelHand;

/**
 *
 * @author adamnark
 */
public class SwingFourPicker implements IFourPicker {

    private JPanelHand jPanelHand;

    public void init(JPanelHand jPanelHand) {
        this.jPanelHand = jPanelHand;
    }

    @Override
    public Collection<Card> pickFour(Hand hand) {
        if (jPanelHand == null) {
            throw new RuntimeException("SwingFourPicker instance not initalized! call init().");
        }

        Collection<Card> four = new LinkedList<>();
        for (JButtonCard jButtonCard : jPanelHand.getjPanelButtonCardList()) {
            if (jButtonCard.isHighlighted()) {
                four.add(jButtonCard.getCardModel());
            }
        }
        
        if (four.size() != 4) {
            throw new InvalidFourRuntimeException();
        }

        return makeFourOrNull(four);
    }

    private Collection<Card> makeFourOrNull(Collection<Card> four) {
        Collection<Card> lst;
        if (four.size() >= 4) {
            lst = new LinkedList<>();
            int count = 0;
            for (Card card : four) {
                if (count >= 4) {
                    break;
                }
                lst.add(card);
                count++;
            }
        } else {
            lst = null;
        }

        return lst;
    }
}