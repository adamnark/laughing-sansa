package engine.Factory;

import engine.players.Player;

/**
 *
 * @author adamnark
 */
public class PlayerItem {

    private String playerName;
    private boolean isHuman;

    public PlayerItem() {
    }

    public PlayerItem(Player p){
        this(p.getName(), p.isHuman());
    }
    
    public PlayerItem(String playerName, boolean isHuman) {
        this.playerName = playerName;
        this.isHuman = isHuman;
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
