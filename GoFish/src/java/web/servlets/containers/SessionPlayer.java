package web.servlets.containers;

import engine.players.Player;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamnark
 */
public class SessionPlayer {

    private Player player;
    private HttpSession session;
    private int logIndex;

    public SessionPlayer(Player player, HttpSession session) {
        this.player = player;
        this.session = session;
        this.logIndex = 0;
    }

    public int getLogIndex() {
        return logIndex;
    }

    public void setLogIndex(int logIndex) {
        this.logIndex = logIndex;
    }

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
