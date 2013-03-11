package examples.LostExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: Liron Blecher Date: 3/11/11
 */
public class LostFrame extends JFrame {

    private IslandPanel islandPanel;

    public LostFrame() throws HeadlessException {
        //what do we want to happen when the user click on the "X" button
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                closePanel();
            }
        });
        setTitle("Lost");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension();
        frameSize.setSize(screenSize.width * 0.5, screenSize.height * 0.5);
        setSize(frameSize);

        //place the frame in the middle of the screen (if value passed is null, otherwise, places
        //it in the middle of the given component
        setLocationRelativeTo(null);

        setJMenuBar(new LostMenu(this));

        Container contentPane = this.getContentPane();
        islandPanel = new IslandPanel();
        contentPane.add(islandPanel, BorderLayout.CENTER);
    }

    public void closePanel() {
        int dialogResult = JOptionPane.showConfirmDialog(
                LostFrame.this,
                "Are you sure you want to leave the island?",
                "After 3 long seasons...",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            this.dispose();
        }
    }
}