package examples.swing.swinghelloworld;

import examples.swing.swinghelloworld.components.JackLabel;
import examples.swing.swinghelloworld.components.KateLabel;
import examples.swing.swinghelloworld.components.SawyerButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Liron Blecher Date: 3/11/11
 */
public class LostFrame extends JFrame {

    private KateLabel kateLabel;
    private SawyerButton sawyerButton;
    private final JackLabel jackLabel;
    Timer timer;

    public LostFrame() {
        //what do we want to happen when the user click on the "X" button
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Lost");

//        setSize(200, 400); //set size manually

        //OR:
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension();
        frameSize.setSize(screenSize.width * 0.5, screenSize.height * 0.5);
        setSize(frameSize);

        //place the frame in the middle of the screen (if value passed is null, otherwise, places
        //it in the middle of the given component
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(BorderFactory.createTitledBorder("The Island"));
        contentPane.add(mainPanel);

        //add something to the main content panel of the JFrame
        kateLabel = new KateLabel();
        mainPanel.add(kateLabel);

        //if we want it to be in the middle - we can wither set it's location manually
        kateLabel.setBounds(50, 50, 100, 75);
        
        //now... lets add another survivor
        jackLabel = new JackLabel();
        jackLabel.setBounds(200, 200, 100, 75);
        mainPanel.add(jackLabel);

        //and the last survivor in the love triangle is...
        sawyerButton = new SawyerButton();

        timer = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sawyerButton.setLocation(sawyerButton.getX() + 5, sawyerButton.getY());
                if (sawyerButton.getX() >= sawyerButton.getParent().getWidth() - sawyerButton.getWidth()) {
                    timer.stop();
                }
            }
        });

        sawyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle kateBounds = kateLabel.getBounds();
                sawyerButton.setLocation(kateBounds.x - sawyerButton.getWidth(), kateBounds.y);
                timer.start();
            }
        });

        sawyerButton.setBounds(50, 200, 100, 75);
        mainPanel.add(sawyerButton);
    }

    public JLabel getKate() {
        return kateLabel;
    }
}