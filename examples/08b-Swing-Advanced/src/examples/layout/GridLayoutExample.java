package examples.layout;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: Liron Blecher
 * Date: 3/25/11
 */
public class GridLayoutExample extends JPanel{

    private static final int SIZE = 10;

    public GridLayoutExample() {
        GridLayout boxLayout = new GridLayout(SIZE, SIZE, 0, 0);
        this.setLayout(boxLayout);

        for (int row=0 ; row < SIZE ; row++) {
            for (int col=0 ; col < SIZE ; col++) {
                Color cellColor = (row+col) % 2 == 0 ? Color.WHITE : Color.BLACK;
                addPanel(cellColor, null, col, row);
            }
        }
    }

    private void addPanel(Color color, String constraint, int col, int row) {
        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createLineBorder(Color.white));
        //if we want to control the minimum sizes of the component, we can set a preferred size
        panel.setPreferredSize(new Dimension(50, 50));

        //there is no guarantee that the layout manager will enforce the minimum size or maximum size
        panel.setMinimumSize(new Dimension(50, 50));
        panel.setBackground(color);
//        this.add(new Square(col,row), constraint);
        this.add(panel, constraint);

    }

    public static void main(String[] args) {
        ExamplesUtils.showExample("GridLayout Example", new GridLayoutExample());
    }
}
