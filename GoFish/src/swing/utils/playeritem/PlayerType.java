/*
 */
package swing.utils.playeritem;

import javax.swing.ImageIcon;
import swing.utils.SwingUtils;

/**
 *
 * @author adam
 */
public enum PlayerType {
    COMPUTER("Computer", "computer_icon.png"),
    HUMAN("Human", "human_icon.png");
    
    public String title;
    public ImageIcon imageIcon;
    PlayerType(String title, String iconFileName){
        this.title = title;
        this.imageIcon = SwingUtils.getImageIcon(iconFileName);
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
