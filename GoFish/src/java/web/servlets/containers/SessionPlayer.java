package web.servlets.containers;

import engine.players.Player;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamnark
 */
public class SessionPlayer {

    private Player player;
    private HttpSession session;
//    private int logIndex;
    private boolean hasThrownFour;
    private boolean hasRequestedCard;
    private long timeStamp;

    public long  getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long  timeStamp) {
        this.timeStamp = timeStamp;
    }

    public SessionPlayer(Player player, HttpSession session) {
        this.player = player;
        this.session = session;
//        this.logIndex = 0;
        this.hasThrownFour = false;
        this.hasRequestedCard = false;
    }

    public void resetPlayState() {
        this.hasThrownFour = false;
        this.hasRequestedCard = false;
    }

    public boolean isHasThrownFour() {
        return hasThrownFour;
    }

    public void setHasThrownFour(boolean hasThrownFour) {
        this.hasThrownFour = hasThrownFour;
    }

    public boolean isHasRequestedCard() {
        return hasRequestedCard;
    }

    public void setHasRequestedCard(boolean hasRequestedCard) {
        this.hasRequestedCard = hasRequestedCard;
    }

//    public int getLogIndex() {
//        return logIndex;
//    }
//
//    public void setLogIndex(int logIndex) {
//        this.logIndex = logIndex;
//    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
