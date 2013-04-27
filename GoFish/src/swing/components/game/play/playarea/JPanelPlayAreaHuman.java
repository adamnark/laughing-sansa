/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play.playarea;

import swing.components.game.play.PlayEvents;
import engine.players.Player;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class JPanelPlayAreaHuman extends JPanel {

    private JPanelActions jPanelActions;
    private JPanelHand jPanelHand;
    private Player playerModel;

    public JPanelPlayAreaHuman() {
        initComponents();
        initListeners();
    }

    public void setPlayer(Player player) {
        this.playerModel = player;
        this.jPanelHand.setHandModel(player.getHand());
        this.jPanelHand.update();
    }

    private void initComponents() {
        this.jPanelActions = new JPanelActions();
        this.jPanelHand = new JPanelHand();
        this.setLayout(new BorderLayout());
        this.add(this.jPanelHand, BorderLayout.CENTER);
        this.add(this.jPanelActions, BorderLayout.SOUTH);

    }

    private void initListeners() {
        this.jPanelActions.addPropertyChangeListener(PlayEvents.EVENT_THROW, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                fireThrow();
            }
        });
        this.jPanelActions.addPropertyChangeListener(PlayEvents.EVENT_REQUEST, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                fireRequst();
            }
        });
        this.jPanelActions.addPropertyChangeListener(PlayEvents.EVENT_SKIP, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                fireSkip();
            }
        });
    }

    private void fireSkip() {
        this.firePropertyChange(PlayEvents.EVENT_SKIP, this.playerModel, this.playerModel.getName());
    }

    private void fireThrow() {
    }

    private void fireRequst() {
    }
}
