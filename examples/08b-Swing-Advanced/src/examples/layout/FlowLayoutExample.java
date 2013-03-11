package examples.layout;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/25/11
 */
public class FlowLayoutExample extends JPanel{

    public FlowLayoutExample() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        addInnerPanel (Color.BLUE, null);
        addInnerPanel (Color.RED,  null);
        addInnerPanel (Color.GREEN,  null);
        addInnerPanel (Color.PINK,  null);
        addInnerPanel (Color.ORANGE,  null);
    }

    private void addInnerPanel(Color color, String constraint) {
        JPanel panel = new JPanel();
        //if we want to control the minimum sizes of the component, we can set a preferred size
        panel.setPreferredSize(new Dimension(50, 50));

        //there is no guarantee that the layout manager will enforce the minimum size or maximum size
        //panel.setMinimumSize(new Dimension(50, 50));
        panel.setBackground(color);
        this.add(panel, constraint);
//        this.add(new Square(7, 7));

    }

    public static void main(String[] args) {
        ExamplesUtils.showExample("FlowLayout Example", new FlowLayoutExample());
    }
}
