package swing.components.game;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author adamnark
 */
public class JPanelCard extends JPanel {

    /**
     * Creates new form NewJPanel2
     */
    public JPanelCard() {
        initComponents();
    }

    private void initComponents() {
        this.setBounds(0, 0, 100, 75);
        this.setLayout(new BorderLayout());
        
        int numberOfColors = 3;
        Color[] colors = {Color.RED, Color.ORANGE, Color.PINK};

        int hPix = this.getHeight() / numberOfColors;
        int wPix = this.getWidth();
        for (int i = 0; i < numberOfColors; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(colors[i]);
            this.setBounds(0, 0, hPix, wPix);
            this.add(panel);
        }



    }
}
