package examples.LostExample.survivors;

import examples.utils.ExamplesUtils;

import javax.swing.*;

/**
 * User: Liron Blecher
 * Date: 3/13/11
 */
public abstract class SurvivorLabel extends JLabel{

    public SurvivorLabel(String name, String iconName) {
        super ();
        this.setText(name);
        this.setIcon(ExamplesUtils.getImageIcon(iconName));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createEtchedBorder());
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}