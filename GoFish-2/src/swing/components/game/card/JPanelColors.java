package swing.components.game.card;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author adamnark
 */
public class JPanelColors extends JPanel {

    //private final List<Color> colors;
    private Map<Color, String> colors;

    public JPanelColors(Map<Color, String> colors) {
        this.colors = colors;
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(100, 70));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //int numberOfColors = this.colors.size();
//        for (int i = 0; i < numberOfColors; i++) {
//            JPanel panel = new JPanel();
//            panel.setBackground(colors.get(i));
//            this.add(panel);

        for (Color color : colors.keySet()) {
            JPanel panel = new JPanel();
            panel.setBackground(color);
            panel.add(new JLabel(colors.get(color)));
            this.add(panel);
        }
    }
}
