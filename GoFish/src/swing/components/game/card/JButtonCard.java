/*
 */
package swing.components.game.card;

import engine.cards.Card;
import engine.cards.Series;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author adam
 */
public class JButtonCard extends JButton {

    public static final String EVENT_CLICKED = "JPanelCard was clicked";
    private JPanelColors jPanelColors;
    private JLabel jLabelCardName;
    private static List<Series> availableSeries;
    private static List<Color> colorList;
    private static boolean isDictionaryInitiated = false;
    private boolean isHighlighted = false;

    public JButtonCard(Card card) {
        if (!isDictionaryInitiated) {
            throw new RuntimeException("JPanelCard class is not initiated! call setAvaiableSeries() before instantiating.");
        }

        initComponents(card);
        initListeners();
    }

    private void initComponents(Card card) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(50, 60));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.jPanelColors = new JPanelColors(generateColorsFromCard(card));
        this.jLabelCardName = new JLabel();
        this.jLabelCardName.setHorizontalAlignment(JLabel.CENTER);
        
        jLabelCardName.setText(card.getName());
        jLabelCardName.setPreferredSize(new Dimension(75,15));

        this.add(jPanelColors);
        this.add(jLabelCardName);
    }

    private void initListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                toggle();
            }
        });
    }

    public boolean isHighlighted() {
        return this.isHighlighted;
    }

    private void toggle() {
        isHighlighted = !isHighlighted;
        if (isHighlighted) {
            highlight();
        } else {
            lowlight();
        }
    }

    public void highlight() {
        this.jLabelCardName.setForeground(Color.RED);
        this.jLabelCardName.setFont(new java.awt.Font("Tahoma", 1, 12));
    }

    public void lowlight() {
        this.jLabelCardName.setForeground(this.getForeground());
        this.jLabelCardName.setFont(new java.awt.Font("Tahoma", 0, 12));
    }

    private List<Color> generateColorsFromCard(Card card) {
        LinkedList<Color> lst = new LinkedList<>();
        for (Series series : card.getSeries()) {
            int i = availableSeries.indexOf(series);
            Color color = colorList.get(i);
            lst.add(color);
        }

        return lst;
    }

    public static void setAvailableSeries(List<Series> availableSeries) {
        JButtonCard.availableSeries = availableSeries;
        initColorList();
    }

    private static void initColorList() {
        colorList = new LinkedList<>();
        generateColors();
        JButtonCard.isDictionaryInitiated = true;
    }

    private static void generateColors() {
        Random rnd = new Random();
        for (Series s : availableSeries) {
            colorList.add(randomColor(rnd));
        }
    }

    private static Color randomColor(Random rnd) {
        int r = rnd.nextInt(255);
        int g = rnd.nextInt(255);
        int b = rnd.nextInt(255);

        return new Color(r, g, b);
    }
}
