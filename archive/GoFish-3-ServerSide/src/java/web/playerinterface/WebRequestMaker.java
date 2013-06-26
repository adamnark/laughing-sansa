package web.playerinterface;

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
public class WebRequestMaker implements IRequestMaker{
    private Request request;

    @Override
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
