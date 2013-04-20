/*
 */
package swing.components.settings.manual.playerItem;

/**
 *
 * @author adam
 */
public class PlayerItem {
    private String name;
    private PlayerType playerType;
    
    public PlayerItem(String name, boolean isHuman) {
        this.name = name;
        playerType = isHuman ? PlayerType.HUMAN : PlayerType.COMPUTER;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
