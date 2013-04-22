package swing.playerinterface;

import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import engine.request.IRequestMaker;
import engine.request.Request;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class SwingRequestMaker implements IRequestMaker{

    @Override
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
