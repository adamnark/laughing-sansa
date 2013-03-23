package engine.players;

import engine.cards.Series;
import java.util.List;

/**
 *
 * @author adamnark
 */
public interface IRequestMaker {
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers);
}
