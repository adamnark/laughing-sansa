/*
 */
package swing.components.game.card;

import engine.cards.Card;
import engine.cards.Series;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author adam
 */
public class JButtonCard extends JButton {

    public static final String EVENT_CLICKED = "JPanelCard was clicked";
    private JPanelColors jPanelColors;
    private static List<Series> availableSeries;
    private static List<Color> colorList;
    private static boolean isDictionaryInitiated = false;
    private boolean isHighlighted = false;
    private Card cardModel;

    public JButtonCard(Card card) {
        if (!isDictionaryInitiated) {
            throw new RuntimeException("JPanelCard class is not initiated! call setAvaiableSeries() before instantiating.");
        }

        this.cardModel = card;
        initComponents();
        initListeners();
    }

    public Card getCardModel() {
        return cardModel;
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(50, 60));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setContentAreaFilled(false);

        this.jPanelColors = new JPanelColors(generateColorsFromCard(this.cardModel));
        this.setToolTipText(this.cardModel.getName());

        this.add(jPanelColors);
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
        this.setBorder(BorderFactory.createLineBorder(Color.red,3));
    }

    public void lowlight() {
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    private Map<Color, String> generateColorsFromCard(Card card) {
        Map<Color, String> map = new HashMap<>();

        for (Series series : card.getSeries()) {
            int i = availableSeries.indexOf(series);
            Color color = colorList.get(i);
            map.put(color, series.getName());
        }

        return map;
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
