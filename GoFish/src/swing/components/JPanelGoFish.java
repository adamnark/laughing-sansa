/*
 * 
 */
package swing.components;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import swing.components.settings.JPanelMainMenu;
import swing.components.settings.manual.JPanelManualGame;
import swing.components.settings.xml.JPanelXMLSettings;

/**
 *
 * @author adam
 */
public class JPanelGoFish extends JPanel {

    public static final String EVENT_EXIT = "Exit Event";
    private static final String CARD_MENU = "Card - main menu";
    private static final String CARD_MANUAL_SETTINGS = "Card - manual settings";
    private static final String CARD_XML_SETTINGS = "Card - XML settings";
    private CardLayout cardLayout;
    private JPanelMainMenu jPanelMainMenu;
    private JPanelManualGame jPanelManualGame;
    private JPanelXMLSettings jPanelXMLSettings;

    public JPanelGoFish() {
        initComponents();
        initCards();
        initListeners();
    }

    private void initComponents() {
        this.setSize(600, 450);
        this.cardLayout = new CardLayout(0, 0);
        this.setLayout(this.cardLayout);

        jPanelMainMenu = new JPanelMainMenu();
        jPanelManualGame = new JPanelManualGame();
        jPanelXMLSettings = new JPanelXMLSettings();
    }

    private void initCards() {
        this.add(jPanelMainMenu, CARD_MENU);
        this.add(jPanelManualGame, CARD_MANUAL_SETTINGS);
        this.add(jPanelXMLSettings, CARD_XML_SETTINGS);
    }

    private void initListeners() {
        initMainMenuListeners();
        initManualSettingsListeners();
        initXMLSettingsListeners();
    }

    private void showMainMenu() {
        cardLayout.show(JPanelGoFish.this, CARD_MENU);
    }

    private void initManualSettingsListeners() {
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_BACK, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                showMainMenu();
            }
        });
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_START_GAME, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                startGame();
            }
        });
    }

    private void initMainMenuListeners() {
        this.jPanelMainMenu.addPropertyChangeListener(JPanelMainMenu.EVENT_GOTO_MANUAL, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                cardLayout.show(JPanelGoFish.this, CARD_MANUAL_SETTINGS);
            }
        });

        this.jPanelMainMenu.addPropertyChangeListener(JPanelMainMenu.EVENT_EXIT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                fireExitEvent();
            }
        });

        this.jPanelMainMenu.addPropertyChangeListener(JPanelMainMenu.EVENT_GOTO_XML, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                cardLayout.show(JPanelGoFish.this, CARD_XML_SETTINGS);
            }
        });

    }

    private void initXMLSettingsListeners() {
        jPanelXMLSettings.addPropertyChangeListener(JPanelXMLSettings.EVENT_BACK, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                showMainMenu();
            }
        });
    }

    private void fireExitEvent() {
        firePropertyChange(EVENT_EXIT, true, false);
    }

    private void startGame() {
        //save the factory for later!
        //jPanelManualGame.getGuiEngineMaker().makeEngine();
    }
}
