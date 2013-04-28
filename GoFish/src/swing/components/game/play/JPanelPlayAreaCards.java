/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import swing.components.game.play.playarea.IPlayAreaPanel;

/**
 *
 * @author Natalie
 */
public class JPanelPlayAreaCards extends JPanel {

    private CardLayout cardLayout;
    private Map<String, IPlayAreaPanel> cardMap;

    public JPanelPlayAreaCards() {
        initComponents();
        //this.cards = new LinkedList<>();
        this.cardMap = new HashMap<>();
    }

    public void addCard(IPlayAreaPanel jPanelPlayArea, String playerName) {
        this.add((JPanel) jPanelPlayArea, playerName);
        this.cardMap.put(playerName, jPanelPlayArea);
    }

    public void showCard(String playerName) {
        this.cardLayout.show(this, playerName);
    }

    private void initComponents() {
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    }

    public void refresh() {
        for (IPlayAreaPanel playArea : cardMap.values()) {
            playArea.refresh();
        }
    }

    public void disableThrowingForCurrentCard() {
        kooki
    }
}
