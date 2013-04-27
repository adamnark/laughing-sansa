/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.play.playarea;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Natalie
 */
public class JPanelPlayAreaHuman extends JPanel{

    private JPanelActions jPanelActions;
    private JPanelHand jPanelHand;
    
    
    public JPanelPlayAreaHuman() {
        initComponents();
    }
    
    public JPanelHand getJPanelHand(){
        return this.jPanelHand;
    }

    private void initComponents() {
        this.jPanelActions = new JPanelActions();
        this.jPanelHand = new JPanelHand();
        this.setLayout(new BorderLayout());
        this.add(this.jPanelHand, BorderLayout.CENTER);
        this.add(this.jPanelActions, BorderLayout.SOUTH);
        
    }
    
}
