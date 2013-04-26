/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.graveyard;

import engine.Engine;
import engine.cards.Card;
import java.util.Collection;
import java.util.LinkedList;
import swing.components.game.card.JButtonCard;

/**
 *
 * @author Natalie
 */
public class JPanelGraveyard extends javax.swing.JPanel {

    Engine engine = null;
    private LinkedList<JButtonCard> jButtonCardList;

    /**
     * Creates new form JPanelGraveyard
     */
    public JPanelGraveyard() {
        this.jButtonCardList = new LinkedList<>();
        initComponents();
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void refresh() {
        empty();
        Collection<Card> lastCards = this.engine.getLastCardsThrown();
        for (Card card : lastCards) {
            addCard(card);
        }
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder("Graveyard"));
        setPreferredSize(new java.awt.Dimension(100, 33));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void empty() {
        for (JButtonCard jButtonCard : jButtonCardList) {
            this.remove(jButtonCard);
        }
        
        this.jButtonCardList = new LinkedList<>();
        this.revalidate();
        this.repaint();
    }

    private void addCard(Card card) {
        JButtonCard jButtonCard = new JButtonCard(card);
        jButtonCard.setEnabled(false);
        this.jButtonCardList.add(jButtonCard);
    }
}
