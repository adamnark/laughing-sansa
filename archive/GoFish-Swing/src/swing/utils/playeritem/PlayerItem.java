/*
 */
package swing.utils.playeritem;

/**
 *
 * @author adam
 */
public class PlayerItem {
    private String name;
    private PlayerType playerType;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
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
