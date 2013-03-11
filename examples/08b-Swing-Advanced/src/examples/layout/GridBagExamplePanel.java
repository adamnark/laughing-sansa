package examples.layout;

import examples.utils.ExamplesUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GridBagExamplePanel extends JPanel {

    private static final int LINE_SIZE = 9;

    public static void main(String[] args) {
        ExamplesUtils.showExample("GridBag Layout Example", new GridBagExamplePanel());
    }

    public GridBagExamplePanel() {
        super();
        initUI();
    }

    private void initUI() {
        //init layout
        this.setLayout(new GridBagLayout());

        List<JComponent> components = new LinkedList<JComponent>();
        for (int i = 0; i < LINE_SIZE * 4; i++) {
            components.add(createInnerPanel("square: " + i));
        }

        Iterator<JComponent> componentIterator = components.iterator();

        //Add Panels for Each of the four sides
        for (int sideIndex = 0; sideIndex < 4; sideIndex++) {
            for (int lineIndex = 0; lineIndex < LINE_SIZE; lineIndex++) {
                JComponent component = componentIterator.next();
                switch (sideIndex) {
                    case 0:
                        //top line
                        addComponent(lineIndex, 0, component);
                        break;
                    case 1:
                        //right line
                        addComponent(LINE_SIZE, lineIndex, component);
                        break;
                    case 2:
                        //bottom line - and in reverse order
                        addComponent(LINE_SIZE - lineIndex, LINE_SIZE, component);
                        break;
                    case 3:
                        //left line - and in reverse order
                        addComponent(0, LINE_SIZE - lineIndex, component);
                        break;
                }
            }
        }

        // Main Inner Area Notice Starts at (1,1) and takes up 11x11
        JPanel innerPanel = createInnerPanel("CENTER");
        this.add(innerPanel,
                new GridBagConstraints(1,
                1,
                6,
                6,
                2, 2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    private JPanel createInnerPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void addComponent(int gridX, int gridY, JComponent component) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridX;
        c.gridy = gridY;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 3;
        c.ipady = 3;
        this.add(component, c);
    }
}