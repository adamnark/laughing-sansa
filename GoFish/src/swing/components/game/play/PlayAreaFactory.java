/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play;

import engine.players.Player;
import javax.swing.JPanel;
import swing.playerinterface.SwingFourPicker;
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
        jPanelC.setPlayerName(player.getName());
        
        return jPanelC;
    }

    private static JPanel makeHumanPlayArea(Player player) {
        JPanelPlayAreaHuman jPanelH = new JPanelPlayAreaHuman();
        jPanelH.setPlayer(player);
        SwingFourPicker swingFourPicker = new SwingFourPicker();
        swingFourPicker.init(jPanelH.getJPanelHand());
        player.setFourPicker(swingFourPicker);
        
        return jPanelH;
    }
}
