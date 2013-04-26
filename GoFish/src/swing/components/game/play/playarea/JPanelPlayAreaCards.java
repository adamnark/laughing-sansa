/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play.playarea;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class JPanelPlayAreaCards extends JPanel {

    private CardLayout cardLayout;

    public JPanelPlayAreaCards() {
        initComponents();
    }
    
    public void addCard(JPanel jPanelPlayArea, String playerName){
        this.cardLayout.addLayoutComponent(jPanelPlayArea, playerName);
    }
    
    public void showCard(String playerName){
        this.cardLayout.show(this, playerName);
    }

    private void initComponents() {
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    }
}
