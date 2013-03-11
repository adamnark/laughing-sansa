/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.LostExample.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author blecherl
 */
public class AboutAction extends AbstractAction{

    private JFrame parent;

    public AboutAction() {
        super("About");
    }

    public AboutAction(JFrame parent) {
        this();
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
                parent,
                "About Lost",
                "Mysterious Message",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
