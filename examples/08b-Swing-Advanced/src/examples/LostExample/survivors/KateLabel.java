package examples.LostExample.survivors;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * There are three ways to implement swing survivors:
 * 1. Implement the interface in the class itself
 * 2. Use an anonymous implementation
 * 3. Use an Adapter (which is an abstract class that implements the interface and have
 * empty implementations of all the interface methods so you can choose which methods
 * to implement yourself)
 *
 * This is a demo on option #3
 */
public class KateLabel extends SurvivorLabel {
    private Random random;

    public KateLabel() {
        super("Kate", "kate.jpg");

        //add an anonymous implementation of a mouse adapter
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                handleMouseEntered();
            }
        });
    }

    private void handleMouseEntered() {
        random = new Random(System.currentTimeMillis());
        Dimension topLevelContainerSize = getParent().getSize();
        int availableWidth = topLevelContainerSize.width - this.getWidth();
        int availableHeight = topLevelContainerSize.height - this.getHeight();
        this.setLocation(random.nextInt(availableWidth), random.nextInt(availableHeight));
        this.repaint();
    }
}