/*
 */
package swing.components.game;

import swing.components.game.card.JButtonCard;
import engine.cards.Card;
import engine.cards.Series;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class JPanelTest extends javax.swing.JPanel {

    /**
     * Creates new form JPanelTest
     */
    public JPanelTest() {
        initComponents();
        LinkedList<Series> series = makeSeries();
        LinkedList<Card> cards = makeCards(series);

        JButtonCard.setAvailableSeries(series);

        for (Card card : cards) {
            JButtonCard jPanelCard = new JButtonCard(card);
            this.add(jPanelCard);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private LinkedList<Series> makeSeries() {
        LinkedList<Series> series = new LinkedList<>();
        for (int i = 0; i < 60; i++) {
            series.add(new Series(i % 5 + " ting"));
        }

        return series;
    }

    private LinkedList<Card> makeCards(LinkedList<Series> series) {
        LinkedList<Card> cards = new LinkedList<>();

        for (int i = 0; i < series.size(); i += 3) {
            Series srs1 = series.get(i);
            Series srs2 = series.get(i + 1);
            Series srs3 = series.get(i + 2);
            Card card = new Card();
            card.addSeries(srs1);
            card.addSeries(srs2);
            card.addSeries(srs3);
            card.setName(srs1.getName());
            cards.add(card);

        }

        return cards;
    }
}