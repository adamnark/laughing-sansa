package examples.LostExample.actions;

import examples.LostExample.survivors.HurleyPanel;
import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class InsultAction extends AbstractAction{
    private HurleyPanel hurleyPanel;

    public InsultAction(HurleyPanel hurleyPanel) {
        super ("Sawyer", ExamplesUtils.getImageIcon("sawyer.jpg"));
        this.hurleyPanel = hurleyPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //two possible ways to show dialogs:
        //1. use the JOptionPane to shortcut the entire creation of the JDialog
        Component parentComponent = e.getSource() instanceof Component ? (Component) e.getSource() : null;
        int userResponse = JOptionPane.showOptionDialog(
                parentComponent,
                "What are you looking at?",
                "I don't wear shirts",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                ExamplesUtils.getImageIcon("sawyer.jpg"),
                new String[] {"Idiot", "Chubby", "Padre"},
                "Idiot");
        switch (userResponse) {
            case JOptionPane.YES_OPTION:
                hurleyPanel.setMayoNumber(hurleyPanel.getMayoNumber()-1);
                break;
            case JOptionPane.NO_OPTION:
                hurleyPanel.setMayoNumber(hurleyPanel.getMayoNumber()+1);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
            default:
                //do nothing

        }
    }
}
