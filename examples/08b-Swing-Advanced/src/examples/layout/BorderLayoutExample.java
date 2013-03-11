package examples.layout;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/25/11
 */
public class BorderLayoutExample extends JPanel{

    public BorderLayoutExample() {
        this.setLayout(new BorderLayout(0, 0));

//        addPanel (Color.BLUE, BorderLayout.CENTER);
        this.add(new GridLayoutExample(), BorderLayout.CENTER);

//        addPanel (Color.RED, BorderLayout.NORTH); //same as BorderLayout.PAGE_START

        this.add(new BoxLayoutExample(), BorderLayout.NORTH);

        addPanel (Color.GREEN, BorderLayout.SOUTH); //same as BorderLayout.PAGE_END
//        this.add(new FlowLayoutExample(), BorderLayout.SOUTH);
        addPanel (Color.PINK, BorderLayout.LINE_START); //same as BorderLayout.EAST
        addPanel (Color.ORANGE, BorderLayout.LINE_END); //same as BorderLayout.WEST
    }

    private void addPanel(Color color, String constraint) {
        JPanel panel = new JPanel();
        //if we want to control the minimum sizes of the component, we can set a preferred size
        panel.setPreferredSize(new Dimension(50, 50));

        //there is no guarantee that the layout manager will enforce the minimum size or maximum size
//        panel.setMinimumSize(new Dimension(50, 50));
//        panel.setMaximumSize(new Dimension(50, 50));

        panel.setBackground(color);
        this.add(panel, constraint);

    }

    public static void main(String[] args) {
        ExamplesUtils.showExample("BorderLayout Example", new BorderLayoutExample());
    }
}
