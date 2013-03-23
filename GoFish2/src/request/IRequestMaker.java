package request;

import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import java.util.List;

/**
 *
 * @author adamnark
 */
public interface IRequestMaker {
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers);
}
