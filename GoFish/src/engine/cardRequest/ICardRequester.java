package engine.cardRequest;

import engine.Player;
import java.util.Collection;

/**
 *
 * @author adamnark
 */
public interface ICardRequester {

    public CardRequest requestCard(Player me, Collection<Player> otherPlayers, Collection<String> availableSerieses);

}
