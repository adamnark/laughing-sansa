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
    private String currentCard;

    public JPanelPlayAreaCards() {
        initComponents();
        //this.cards = new LinkedList<>();
        this.cardMap = new HashMap<>();
        this.currentCard = null;
    }

    public void addCard(IPlayAreaPanel jPanelPlayArea, String playerName) {
        this.add((JPanel) jPanelPlayArea, playerName);
        this.cardMap.put(playerName, jPanelPlayArea);
        if (currentCard == null) {
            currentCard = playerName;
        }
    }

    public void showCard(String playerName) {
        resetCard(playerName);
        this.cardLayout.show(this, playerName);
        this.currentCard = playerName;
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
        cardMap.get(this.currentCard).disableThrowing();
    }

    public void disableRequestingForCurrentCard() {
        cardMap.get(this.currentCard).disableRequsting();
    }

    private void resetCard(String playerName) {
        cardMap.get(playerName).enableRequsting();
        cardMap.get(playerName).enableThrowing();
    }
}
