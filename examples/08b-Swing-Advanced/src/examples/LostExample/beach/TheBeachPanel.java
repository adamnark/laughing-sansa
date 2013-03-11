package examples.LostExample.beach;

import examples.LostExample.IslandPanel;
import examples.LostExample.actions.InsultAction;
import examples.LostExample.survivors.*;

import javax.swing.*;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class TheBeachPanel extends JPanel{

    public TheBeachPanel(IslandPanel islandPanel) {
        setLayout(null);

        setBorder(BorderFactory.createTitledBorder("The Beach"));

        JLabel kateLabel = new KateLabel();
        add(kateLabel);
        kateLabel.setBounds(50, 50, 100, 75);

        JackLabel jackLabel = new JackLabel();
        jackLabel.setBounds(200, 200, 100, 75);
        jackLabel.addMouseListener(jackLabel);
        add(jackLabel);

        LockeButton lockButton = new LockeButton(islandPanel);
        lockButton.setBounds(100, 300, 100, 75);
        add(lockButton);

        HurleyPanel hurleyPanel = new HurleyPanel();
        hurleyPanel.setBounds(250, 300, 200, 150);
        add(hurleyPanel);

        InsultAction insultAction = new InsultAction(hurleyPanel);
        
        SawyerButton sawyerButton = new SawyerButton(insultAction);
        sawyerButton.setBounds(50, 200, 100, 75);
        add(sawyerButton);
    }
}