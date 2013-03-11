package examples.LostExample.survivors;

import examples.LostExample.IslandPanel;
import examples.LostExample.beach.TheBeachPanel;
import examples.LostExample.others.OthersCampPanel;
import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class LockeButton extends JButton{
    private IslandPanel islandPanel;

    public LockeButton(IslandPanel islandPanel) {
        super ("John Locke", ExamplesUtils.getImageIcon("locke.jpg"));
        this.islandPanel = islandPanel;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LockeButton lockButton = LockeButton.this;
                TheBeachPanel beachPanel = lockButton.islandPanel.getBeachPanel();
                OthersCampPanel othersCampPanel = lockButton.islandPanel.getOthersCampPanel();

                if (lockButton.getParent() == beachPanel) {
                    beachPanel.remove(lockButton);
                    othersCampPanel.add(lockButton);
                } else {
                    othersCampPanel.remove(lockButton);
//                    lockButton.setBounds(50, 400, 100, 50);
                    beachPanel.add(lockButton);
                }
                
//                beachPanel.invalidate();
//                beachPanel.validate();
                beachPanel.repaint();
//                othersCampPanel.invalidate();
                othersCampPanel.validate();
                othersCampPanel.repaint();
            }
        });
    }
}
