/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play;

import engine.players.Player;
import javax.swing.JPanel;
import swing.components.game.play.playarea.IPlayAreaPanel;
import swing.playerinterface.SwingFourPicker;
import swing.components.game.play.playarea.JPanelPlayAreaComputer;
import swing.components.game.play.playarea.JPanelPlayAreaHuman;

/**
 *
 * @author Natalie
 */
public class PlayAreaFactory {

    public static IPlayAreaPanel makeJPanelPlayArea(Player player) {
        IPlayAreaPanel jPanel;
        if (player.isHuman()) {
            jPanel = makeHumanPlayArea(player);
        } else {
            jPanel = makeComputerPlayArea(player);
        }

        return jPanel;
    }

    private static IPlayAreaPanel makeComputerPlayArea(Player player) {
        JPanelPlayAreaComputer jPanelC = new JPanelPlayAreaComputer();
        jPanelC.setPlayerName(player.getName());
        
        return jPanelC;
    }

    private static IPlayAreaPanel makeHumanPlayArea(Player player) {
        JPanelPlayAreaHuman jPanelH = new JPanelPlayAreaHuman();
        jPanelH.setPlayer(player);
        SwingFourPicker swingFourPicker = new SwingFourPicker();
        swingFourPicker.init(jPanelH.getJPanelHand());
        player.setFourPicker(swingFourPicker);
        
        return jPanelH;
    }
}
