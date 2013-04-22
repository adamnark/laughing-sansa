/*
 * 
 */
package swing.components;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import swing.components.settings.JPanelSettingsMenu;
import swing.components.settings.manual.JPanelManualGame;

/**
 *
 * @author adam
 */
public class JPanelGoFish extends JPanel {

    JPanelSettingsMenu jPanelSettingsMenu;
    JPanelManualGame jPanelManualGame;
    CardLayout cardLayout;

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
        this.add(jPanelSettingsMenu, "SettingsMenu");
        this.add(jPanelManualGame, "SettingsManual");
    }

    private void initListeners() {
        this.jPanelSettingsMenu.addButtonManualGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(JPanelGoFish.this, "SettingsManual");
            }
        });

        this.jPanelManualGame.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(JPanelGoFish.this, "SettingsMenu");
            }
        });
    }
}
