package examples.LostExample.survivors;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * There are three ways to implement swing survivors:
 * 1. Implement the interface in the class itself
 * 2. Use an anonymous implementation
 * 3. Use an Adapter (which is an abstract class that implements the interface and have
 * empty implementations of all the interface methods so you can choose which methods
 * to implement yourself)
 *
 * This is a demo on option #1
 */
public class JackLabel extends SurvivorLabel implements MouseListener{

    public JackLabel() {
        super("Jack", "jack.jpg");
        //register myself as a mouse listener
//        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(
                this, "We have to go back!", "I have a beard", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}