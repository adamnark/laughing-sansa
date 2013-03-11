package examples.LostExample.survivors;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/20/11
 */
public class HurleyPanel extends JPanel{
    private JLabel hurleyLabel;
    private JPanel mayoPanel;
    private int mayoNumber = 0;

    public HurleyPanel() {
        initComponents();
        initUI();
    }

    private void initComponents() {
        hurleyLabel = new SurvivorLabel("Hurley", "hurley.jpg") {};
        hurleyLabel.setBorder(BorderFactory.createEmptyBorder());

        mayoPanel = new JPanel ();
        BoxLayout boxLayout = new BoxLayout(mayoPanel, BoxLayout.X_AXIS);
        mayoPanel.setLayout(boxLayout);
        mayoPanel.setOpaque(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(new Color(250, 200, 100));
        add(hurleyLabel, BorderLayout.PAGE_START);
        add(mayoPanel, BorderLayout.PAGE_END);
    }

    public void setMayoNumber(int mayoNumber) {
        this.mayoNumber = mayoNumber;
        mayoPanel.removeAll();
        for (int i=0 ; i < mayoNumber ; i++) {
//            mayoPanel.add (new JLabel (ExamplesUtils.getImageIcon("mayo.jpg")));
            mayoPanel.add (new JLabel (ExamplesUtils.getImageIcon("mayo2.png")));
        }
        this.validate();
    }

    public int getMayoNumber() {
        return mayoNumber;
    }
}
