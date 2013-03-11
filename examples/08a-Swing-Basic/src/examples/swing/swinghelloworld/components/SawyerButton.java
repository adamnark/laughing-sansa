package examples.swing.swinghelloworld.components;

import examples.swing.utils.ExamplesUtils;

import javax.swing.*;

/**
 * User: Liron Blecher
 * Date: 3/13/11
 *
 * For buttons and menu items you can specify an action instead of implementing
 * a mouse listener
 */
public class SawyerButton extends JButton {
    public SawyerButton() {
        this.setText("Sawyer");
        this.setIcon(ExamplesUtils.getImageIcon("sawyer.jpg"));
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
    }


}