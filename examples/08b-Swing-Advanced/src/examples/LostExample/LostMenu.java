package examples.LostExample;

import examples.LostExample.actions.AboutAction;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class LostMenu extends JMenuBar {

    public LostMenu(final LostFrame lostFrame) throws HeadlessException {
        JMenu fileMenu = new JMenu("File");
        this.add (fileMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lostFrame.closePanel();
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        this.add(helpMenu);

        AboutAction aboutAction = new AboutAction(lostFrame);
        JMenuItem aboutMenuItem = new JMenuItem(aboutAction);
        helpMenu.add(aboutMenuItem );
    }
}
