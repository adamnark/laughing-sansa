package examples.LostExample.survivors;

import javax.swing.*;

/**
 * User: Liron Blecher
 * Date: 3/13/11
 *
 * For buttons and menu items you can specify an action instead of implementing
 * a mouse listener
 */
public class SawyerButton extends JButton {
    public SawyerButton(Action action) {
        super(action);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
    }
}