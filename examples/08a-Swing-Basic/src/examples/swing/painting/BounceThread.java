package examples.swing.painting;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author blecherl
 */
public class BounceThread {

    public static void main(String[] args) {
        JFrame frame = new BounceThreadFrame();
        frame.setVisible(true);
    }
}

class BounceThreadFrame extends JFrame {

    public BounceThreadFrame() {
        setSize(300, 200);
        setTitle("Bounce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        canvas = new JPanel();
        contentPane.add(canvas);
        JPanel buttonsPannel = new JPanel();
        addButton(buttonsPannel, "Start", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Ball b = new Ball(canvas);
                b.start();
            }
        });
        contentPane.add(buttonsPannel, BorderLayout.SOUTH);
    }

    public void addButton(Container c, String title, ActionListener a) {
        JButton b = new JButton(title);
        c.add(b);
        b.addActionListener(a);
    }
    private JPanel canvas;
}

class Ball extends Thread {

    private JPanel box;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;
    public Ball(JPanel b) {
        box = b;
        x = new Random().nextInt(b.getWidth() - XSIZE);
        y = new Random().nextInt(b.getHeight() - YSIZE);
    }

    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void move() {
        if (!box.isVisible()) {
            return;
        }
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.width) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.height) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void run() {
        try {
            draw();
            for (int i = 1; i <= 1000; i++) {
                move();
                sleep(5);
            }
        } catch (InterruptedException e) {
        }
    }
}