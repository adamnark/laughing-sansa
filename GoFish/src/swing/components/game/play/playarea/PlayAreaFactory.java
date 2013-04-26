/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play.playarea;

import engine.players.Player;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class PlayAreaFactory {

    public static JPanel makeJPanelPlayArea(Player player) {
        JPanel jPanel;
        if (player.isHuman()) {
            jPanel = makeHumanPlayArea(player);
        } else {
            jPanel = makeComputerPlayArea(player);
        }
        
        return jPanel;
    }

    private static JPanel makeComputerPlayArea(Player player) {
        JPanelPlayAreaComputer jPanelC = new JPanelPlayAreaComputer();

        return jPanelC;
    }

    private static JPanel makeHumanPlayArea(Player player) {
        JPanelPlayAreaHuman jPanelH = new JPanelPlayAreaHuman();
        jPanelH.getJPanelHand().setHandModel(player.getHand());

        return jPanelH;
    }
}
