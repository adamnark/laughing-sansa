package examples.LostExample;

import examples.LostExample.beach.TheBeachPanel;
import examples.LostExample.others.OthersCampPanel;
import examples.LostExample.others.stations.StationsManager;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class IslandPanel extends JPanel {
    private TheBeachPanel beachPanel;
    private OthersCampPanel othersCampPanel;
    private StationsManager stationsManager;
    private JSplitPane mainHorizontalSplitPane;

    public IslandPanel() {
        initComponents();
        initUI();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainHorizontalSplitPane.setDividerLocation(0.4);
            }
        });
    }

    private void initComponents() {
        beachPanel = new TheBeachPanel(this);
        othersCampPanel = new OthersCampPanel();
        stationsManager = new StationsManager();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder("The Island"));

        mainHorizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //define how to disburse free space between the split pane components
        mainHorizontalSplitPane.setResizeWeight(0.4);
        this.add(mainHorizontalSplitPane, BorderLayout.CENTER);

        //the beach
        mainHorizontalSplitPane.setLeftComponent(beachPanel);

        //the others
        JSplitPane rightVerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        final JScrollPane jScrollPane = new JScrollPane(othersCampPanel);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightVerticalSplitPane.setTopComponent(jScrollPane);
        rightVerticalSplitPane.setBottomComponent(stationsManager);
        rightVerticalSplitPane.setResizeWeight(0.2);
        mainHorizontalSplitPane.setRightComponent(rightVerticalSplitPane);
    }

    public TheBeachPanel getBeachPanel() {
        return beachPanel;
    }

    public OthersCampPanel getOthersCampPanel() {
        return othersCampPanel;
    }
}
