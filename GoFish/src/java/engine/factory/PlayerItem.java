package engine.factory;

import engine.players.Player;

/**
 *
 * @author adamnark
 */
public class PlayerItem {

    private String playerName;
    private boolean isHuman;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerItem() {
    }

    public PlayerItem(Player p){
        this(p.getName(), p.isHuman(), p.getScore());
    }
    
    public PlayerItem(String playerName, boolean isHuman, int score) {
        this.playerName = playerName;
        this.isHuman = isHuman;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }
}
