/*
 */
package swing.components.settings.manual;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultListModel;
import swing.utils.playeritem.PlayerItem;
import swing.utils.playeritem.PlayerItemCollection;
import swing.utils.playeritem.PlayerItemRenderer;
import swing.utils.playeritem.exceptions.DuplicateNameException;
import swing.utils.playeritem.exceptions.TooManyPlayersException;

/**
 *
 * @author adam
 */
public class JPanelManualGame extends javax.swing.JPanel {

    public static final String EVENT_START = "Start Game Event Manual";
    public static final String EVENT_BACK = "Back Event Manual";
    private DefaultListModel<PlayerItem> listModel;
    private PlayerItemCollection playerItemsCollection;
    private GUIEngineMaker guiEngineMaker;

    /**
     * Creates new form JPanelManualSub
     */
    public JPanelManualGame() {
        listModel = new DefaultListModel<>();
        playerItemsCollection = new PlayerItemCollection();
        initComponents();
        addMockPlayers();
        initListeners();
    }

    private void initGUIMaker() {
        this.guiEngineMaker = new GUIEngineMaker();
        this.guiEngineMaker.setAllowMutipleRequests(this.getAllowMutipleRequests());
        this.guiEngineMaker.setForceShowOfSeries(this.getForceShowOfCards());
        for (PlayerItem playerItem : playerItemsCollection.getList()) {
            this.guiEngineMaker.addPlayer(playerItem);
        }
    }

    public GUIEngineMaker getGuiEngineMaker() {
        return guiEngineMaker;
    }

    private boolean getAllowMutipleRequests() {
        return this.jCheckBoxAllowMutipleRequests.isSelected();
    }

    private boolean getForceShowOfCards() {
        return this.jCheckBoxForceShowOfSeries.isSelected();
    }

    public void addBackButtonListener(ActionListener al) {
        this.jButtonBack.addActionListener(al);
    }

    private void initListeners() {
        this.jPanelAddPlayer1.addPropertyChangeListener(
                JPanelAddPlayer.ADD_PLAYER_EVENT,
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                addPlayer();
            }
        });
    }

    private void addPlayer() {
        String playerName = jPanelAddPlayer1.getPlayerName();
        boolean isHuman = jPanelAddPlayer1.isPlayerHuman();
        try {
            PlayerItem pi = this.playerItemsCollection.addPlayer(playerName, isHuman);
            this.listModel.addElement(pi);
            jPanelAddPlayer1.clear();
        } catch (DuplicateNameException ex) {
            jPanelAddPlayer1.showErrorMessage("This name already exists!");
        } catch (TooManyPlayersException ex) {
            jPanelAddPlayer1.showErrorMessage("Too many players, no more room!");
        }
    }

    private void removePlayer() {
        int selection = this.jListPlayers.getSelectedIndex();
        if (selection > -1) {
            PlayerItem pi = this.listModel.getElementAt(selection);
            playerItemsCollection.removePlayer(pi.getName());
            listModel.removeElement(pi);
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

        jCheckBoxAllowMutipleRequests = new javax.swing.JCheckBox();
        jCheckBoxForceShowOfSeries = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPlayers = new javax.swing.JList();
        jButtonStart = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jPanelTitle1 = new swing.utils.JPanelTitle();
        jButtonRemovePlayer = new javax.swing.JButton();
        jPanelAddPlayer1 = new swing.components.settings.manual.JPanelAddPlayer();

        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));

        jCheckBoxAllowMutipleRequests.setText("Allow Mutiple Requests");
        jCheckBoxAllowMutipleRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAllowMutipleRequestsActionPerformed(evt);
            }
        });

        jCheckBoxForceShowOfSeries.setText("Force Show Of Series");

        jListPlayers.setModel(listModel);
        jListPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListPlayers.setCellRenderer(new PlayerItemRenderer());
        jScrollPane1.setViewportView(jListPlayers);

        jButtonStart.setText("Start Game");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jPanelTitle1.setTitle("Manual Game");

        jButtonRemovePlayer.setText("Remove selected player");
        jButtonRemovePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemovePlayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonRemovePlayer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxAllowMutipleRequests)
                            .addComponent(jCheckBoxForceShowOfSeries)
                            .addComponent(jPanelAddPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanelTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelAddPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxAllowMutipleRequests)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxForceShowOfSeries)
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemovePlayer)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxAllowMutipleRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAllowMutipleRequestsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxAllowMutipleRequestsActionPerformed

    private void jButtonRemovePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemovePlayerActionPerformed
        removePlayer();
    }//GEN-LAST:event_jButtonRemovePlayerActionPerformed

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        if (isEnoughPlayers()) {
            initGUIMaker();
            firePropertyChange(EVENT_START, false, true);
        } else {
            this.jPanelAddPlayer1.showErrorMessage("Not enough players! add some more.");
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        firePropertyChange(EVENT_BACK, false, true);
    }//GEN-LAST:event_jButtonBackActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonRemovePlayer;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JCheckBox jCheckBoxAllowMutipleRequests;
    private javax.swing.JCheckBox jCheckBoxForceShowOfSeries;
    private javax.swing.JList jListPlayers;
    private swing.components.settings.manual.JPanelAddPlayer jPanelAddPlayer1;
    private swing.utils.JPanelTitle jPanelTitle1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void addMockPlayers() {
        try {
            this.playerItemsCollection.addPlayer("Moxie", true);
            this.playerItemsCollection.addPlayer("Noxie", false);
        } catch (DuplicateNameException ex) {
            System.out.println("oups1");
        } catch (TooManyPlayersException ex) {
            System.out.println("oups2");
        }

        for (PlayerItem playerItem : this.playerItemsCollection.getList()) {
            this.listModel.addElement(playerItem);
        }
    }

    private boolean isEnoughPlayers() {
        return this.playerItemsCollection.getList().size() >= 3;
    }
}
