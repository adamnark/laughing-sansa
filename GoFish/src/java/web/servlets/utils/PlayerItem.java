package web.servlets.utils;

/**
 *
 * @author adamnark
 */
public class PlayerItem {

    private String playerName;
    private boolean isHuman;

    public PlayerItem() {
    }

    
    public String getImgTag(){
        String path = this.isHuman() ? "img/icon_guy.png" : "img/icon_thomas.png";
        return "<img src='" + path + "'>";
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
