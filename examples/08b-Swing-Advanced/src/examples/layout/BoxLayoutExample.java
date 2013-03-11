package examples.layout;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/25/11
 */
public class BoxLayoutExample extends JPanel{

    public BoxLayoutExample() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayout);

        addPanel (Color.BLUE, null);
        addPanel (Color.RED,  null);
        addPanel (Color.GREEN,  null);
        addPanel (Color.PINK,  null);
        addPanel (Color.ORANGE,  null);
    }

    private void addPanel(Color color, String constraint) {
        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createLineBorder(Color.white));
        //if we want to control the minimum sizes of the component, we can set a preferred size
        panel.setPreferredSize(new Dimension(50, 50));

        //there is no guarantee that the layout manager will enforce the minimum size or maximum size
//        panel.setMinimumSize(new Dimension(50, 50));
        panel.setBackground(color);
        this.add(panel, constraint);

    }

    public static void main(String[] args) {
        ExamplesUtils.showExample("BoxLayout Example", new BoxLayoutExample());
    }
}
