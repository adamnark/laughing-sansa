/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game.playerlist;

import engine.Engine;
import engine.players.Player;
import javax.swing.DefaultListModel;
import swing.utils.playeritem.PlayerItem;
import swing.utils.playeritem.PlayerItemCollection;
import swing.utils.playeritem.PlayerItemRenderer;

/**
 *
 * @author Natalie
 */
public class JPanelPlayerList extends javax.swing.JPanel {

    Engine engine;
    private DefaultListModel<PlayerItem> listModel;
    private PlayerItemCollection playerItemsCollection;

    /**
     * Creates new form JPanelPlayerList
     */
    public JPanelPlayerList() {
        this.listModel = new DefaultListModel<>();
        this.playerItemsCollection = new PlayerItemCollection();
        initComponents();
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        addAllPlayers();
        refreshCurrentPlayer();
        validate();
        repaint();
    }

    public void refreshCurrentPlayer() {
        Player currentPlayer = engine.getCurrentPlayer();
        PlayerItem pi = this.playerItemsCollection.getPlayerItem(currentPlayer.getName());
        int index = this.listModel.indexOf(pi);
        this.jListPlayers.setSelectedIndex(index);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListPlayers = new javax.swing.JList();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Player List"));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jListPlayers.setModel(this.listModel);
        jListPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListPlayers.setFocusable(false);
        jListPlayers.setPreferredSize(new java.awt.Dimension(100, 100));
        jListPlayers.setCellRenderer(new PlayerItemRenderer());
        jScrollPane1.setViewportView(jListPlayers);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jListPlayers;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void addAllPlayers() {
        for (Player player : this.engine.getPlayers()) {
            PlayerItem pi = this.playerItemsCollection.addPlayer(player.getName(), player.isHuman());
            this.listModel.addElement(pi);
        }
    }
}
