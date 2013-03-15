package engine.cardRequest;

import engine.Player;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public interface ICardRequester {
    public CardRequest requestCard(LinkedList<Player> players, LinkedList<LinkedList<String>> availableCards);
    public void setPlayerNumber(int playerIndex);
}
