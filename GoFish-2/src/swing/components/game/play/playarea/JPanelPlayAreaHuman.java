/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play.playarea;

import engine.players.Player;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import swing.components.game.play.PlayEvents;

/**
 *
 * @author Natalie
 */
public class JPanelPlayAreaHuman extends JPanel implements IPlayAreaPanel {

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
        this.jPanelHand.setPlayerName(player.getName());
        this.jPanelHand.refresh();
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
        this.firePropertyChange(PlayEvents.EVENT_THROW, this.playerModel, this.playerModel.getName());

    }

    private void fireRequst() {
        this.firePropertyChange(PlayEvents.EVENT_REQUEST, this.playerModel, this.playerModel.getName());
    }

    public JPanelHand getJPanelHand() {
        return jPanelHand;
    }

    @Override
    public void refresh() {
        this.jPanelHand.refresh();
    }

    @Override
    public void disableThrowing() {
        this.jPanelActions.diableThrowing();
    }

    @Override
    public void disableRequsting() {
        this.jPanelActions.disableRequsting();
    }

    @Override
    public void enableThrowing() {
        this.jPanelActions.enableThrowing();
    }

    @Override
    public void enableRequsting() {
        this.jPanelActions.enableRequsting();
    }
}
