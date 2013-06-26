/*
 * 
 */
package swing.components;

import engine.Engine;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import swing.components.game.JPanelGame;
import swing.components.settings.JPanelMainMenu;
import swing.components.settings.manual.GUIEngineMaker;
import swing.components.settings.manual.JPanelManualGame;
import swing.components.settings.xml.JPanelXMLSettings;
import xml.SettingsFromXML;

/**
 *
 * @author adam
 */
public class JPanelGoFish extends JPanel {

    public static final String EVENT_EXIT = "Exit event";
    private static final String CARD_MENU = "Card - main menu";
    private static final String CARD_MANUAL_SETTINGS = "Card - manual settings";
    private static final String CARD_XML_SETTINGS = "Card - xml settings";
    private static final String CARD_GAME = "Card - game";
    private CardLayout cardLayout;
    private JPanelMainMenu jPanelMainMenu;
    private JPanelManualGame jPanelManualGame;
    private JPanelXMLSettings jPanelXMLSettings;
    private JPanelGame jPanelGame;
    private GUIEngineMaker guiEngineMaker;
    private SettingsFromXML settingsFromXML;
    private boolean isLastGameManual;

    public JPanelGoFish() {
        initComponents();
        initCards();
        initListeners();
        guiEngineMaker = null;
        settingsFromXML = null;
    }

    public void showMainMenu() {
        if (this.settingsFromXML != null || this.guiEngineMaker != null) {
            this.jPanelMainMenu.enableButtonReplay();
        }

        cardLayout.show(this, CARD_MENU);
    }

    private void initComponents() {
        this.setSize(600, 450);
        this.cardLayout = new CardLayout(0, 0);
        this.setLayout(this.cardLayout);

        this.jPanelMainMenu = new JPanelMainMenu();
        this.jPanelManualGame = new JPanelManualGame();
        this.jPanelXMLSettings = new JPanelXMLSettings();
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

    private void initManualSettingsListeners() {
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_BACK, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                showMainMenu();
            }
        });
        this.jPanelManualGame.addPropertyChangeListener(JPanelManualGame.EVENT_START, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                startManualGame();
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

        this.jPanelMainMenu.addPropertyChangeListener(JPanelMainMenu.EVENT_REPLAY, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                replayWithLastSettings();
            }
        });
    }

    private void replayWithLastSettings() {
        Engine engine;
        if (!isLastGameManual) {
            engine = this.settingsFromXML.makeEngine();
        } else {
            engine = this.guiEngineMaker.makeEngine();
        }
        startGame(engine);
    }

    private void initXMLSettingsListeners() {
        jPanelXMLSettings.addPropertyChangeListener(JPanelXMLSettings.EVENT_BACK, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                showMainMenu();
            }
        });

        jPanelXMLSettings.addPropertyChangeListener(JPanelXMLSettings.EVENT_START, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                startXMLGame();
            }
        });
    }

    private void fireExitEvent() {
        firePropertyChange(EVENT_EXIT, true, false);
    }

    private void startXMLGame() {
        isLastGameManual = false;
        this.settingsFromXML = this.jPanelXMLSettings.getSettingsFromXML();
        Engine engine = this.settingsFromXML.makeEngine();
        startGame(engine);
    }

    private void startManualGame() {
        isLastGameManual = true;
        this.guiEngineMaker = jPanelManualGame.getGuiEngineMaker();
        Engine engine = this.guiEngineMaker.makeEngine();
        startGame(engine);
    }

    private void startGame(Engine engine) {
        engine.startGame();
        makeGameCard();
        this.jPanelGame.initGame(engine);
        showGameCard();
    }

    private void makeGameCard() {
        this.jPanelGame = new JPanelGame();
        initGameListeners();
        this.add(jPanelGame, CARD_GAME);
    }

    private void showGameCard() {
        cardLayout.show(this, CARD_GAME);
    }

    private void initGameListeners() {
        this.jPanelGame.addPropertyChangeListener(JPanelGame.EVENT_GAME_OVER, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                showMainMenu();
            }
        });
    }
}
