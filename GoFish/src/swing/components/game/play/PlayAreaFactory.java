/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play;

import engine.players.Player;
import javax.swing.JPanel;
import swing.components.game.play.playarea.JPanelHand;
import swing.components.game.play.playarea.JPanelPlayAreaComputer;
import swing.components.game.play.playarea.JPanelPlayAreaHuman;

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
        initJPanelHand(player, jPanelH.getJPanelHand());
        return jPanelH;
    }

    private static void initJPanelHand(Player player, JPanelHand jPanelHand) {
        jPanelHand.setHandModel(player.getHand());
        jPanelHand.setPlayerName(player.getName());
        jPanelHand.update();
    }
}
