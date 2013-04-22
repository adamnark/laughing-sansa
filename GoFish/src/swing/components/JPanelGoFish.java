/*
 * 
 */
package swing.components;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import swing.components.settings.JPanelSettingsMenu;
import swing.components.settings.manual.JPanelManualGame;

/**
 *
 * @author adam
 */
public class JPanelGoFish extends JPanel {

    public static final String EVENT_EXIT = "Exit Event";
    
    private static final String CARD_MENU = "Card - main menu";
    private static final String CARD_MANUAL_SETTINGS = "Card - manual settings";
    
    private JPanelSettingsMenu jPanelSettingsMenu;
    private JPanelManualGame jPanelManualGame;
    private CardLayout cardLayout;

    public JPanelGoFish() {
        initComponents();
        initCards();
        initListeners();
    }

    private void initComponents() {
        this.setSize(600, 450);
        this.cardLayout = new CardLayout(0, 0);
        this.setLayout(this.cardLayout);

        jPanelSettingsMenu = new JPanelSettingsMenu();
        jPanelManualGame = new JPanelManualGame();
    }

    private void initCards() {
        this.add(jPanelSettingsMenu, CARD_MENU);
        this.add(jPanelManualGame, CARD_MANUAL_SETTINGS);
    }

    private void initListeners() {
        this.jPanelSettingsMenu.addButtonManualGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(JPanelGoFish.this, CARD_MANUAL_SETTINGS);
            }
        });
        
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_BACK, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                cardLayout.show(JPanelGoFish.this, CARD_MENU);
            }
        } );

        this.jPanelSettingsMenu.addPropertyChangeListener(JPanelSettingsMenu.EXIT_EVENT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                fireExitEvent();
            }
        });
        
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_START_GAME, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                startGame();
            }
        });
    }

    private void fireExitEvent() {
        firePropertyChange(EVENT_EXIT, true, false);
    }
    
    private void startGame(){
        
    }
}
