/*
 */
package swing.components.init____old.manual;

import swing.components.settings.manual.playerItem.PlayerItem;
import swing.components.settings.manual.playerItem.PlayerItemRenderer;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author adam
 */
public class PanelManualSettings extends JPanel {

    private List<PlayerItem> playerItems;
    private DefaultListModel<PlayerItem> playersListModel;

    public PanelManualSettings() {
        initComponents();
        initPlayerItemList();
        initPlayersListModel();

        playerItems.add(new PlayerItem("Trixie", true));
        playerItems.add(new PlayerItem("Dixie", false));
        playerItems.add(new PlayerItem("Mixie", true));
        playerItems.add(new PlayerItem("Kixie", true));
        playerItems.add(new PlayerItem("Nixie", false));

        for (PlayerItem playerItem : playerItems) {
            playersListModel.addElement(playerItem);
        }

    }

    private void initPlayerItemList() {
        playerItems = new LinkedList<>();
    }

    private void initPlayersListModel() {
        playersListModel = new DefaultListModel<>();
        listPlayerItems.setModel(playersListModel);
        listPlayerItems.setCellRenderer(new PlayerItemRenderer());
        /* button actions go here:
         * hideButton.setAction(new StationHideAction(this));
         */


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        checkBoxAllowMultipleRequests = new javax.swing.JCheckBox();
        checkBoxForceShowOfSeries = new javax.swing.JCheckBox();
        panelAddPlayer = new javax.swing.JPanel();
        labelPlayerName = new javax.swing.JLabel();
        textFieldPlayerName = new javax.swing.JTextField();
        checkBoxIsHuman = new javax.swing.JCheckBox();
        buttonAddPlayer = new javax.swing.JButton();
        buttonStart = new javax.swing.JButton();
        panelPlayersList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPlayerItems = new javax.swing.JList();

        setName(""); // NOI18N
        
        this.setSize(600, 400);

        checkBoxAllowMultipleRequests.setText("Allow mutiple requests");
        /*        checkBoxAllowMultipleRequests.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
         checkBoxAllowMultipleRequestsActionPerformed(evt);
         }
         });*/

        checkBoxForceShowOfSeries.setText("Force show of series");

        panelAddPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Player"));

        labelPlayerName.setText("Player name:");

        checkBoxIsHuman.setText("Player is human");

        buttonAddPlayer.setText("Add Player");

        javax.swing.GroupLayout panelAddPlayerLayout = new javax.swing.GroupLayout(panelAddPlayer);
        panelAddPlayer.setLayout(panelAddPlayerLayout);
        panelAddPlayerLayout.setHorizontalGroup(
                panelAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddPlayerLayout.createSequentialGroup()
                .addComponent(checkBoxIsHuman)
                .addGap(0, 188, Short.MAX_VALUE))
                .addGroup(panelAddPlayerLayout.createSequentialGroup()
                .addComponent(labelPlayerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldPlayerName))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddPlayerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonAddPlayer)))
                .addContainerGap()));
        panelAddPlayerLayout.setVerticalGroup(
                panelAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAddPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelPlayerName)
                .addComponent(textFieldPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxIsHuman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAddPlayer)
                .addContainerGap(15, Short.MAX_VALUE)));

        buttonStart.setText("START GAME");

        panelPlayersList.setBorder(javax.swing.BorderFactory.createTitledBorder("Players"));

        jScrollPane1.setViewportView(listPlayerItems);

        javax.swing.GroupLayout panelPlayersListLayout = new javax.swing.GroupLayout(panelPlayersList);
        panelPlayersList.setLayout(panelPlayersListLayout);
        panelPlayersListLayout.setHorizontalGroup(
                panelPlayersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPlayersListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap()));
        panelPlayersListLayout.setVerticalGroup(
                panelPlayersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPlayersListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(panelAddPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkBoxAllowMultipleRequests)
                .addComponent(checkBoxForceShowOfSeries)
                .addComponent(buttonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelPlayersList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(panelPlayersList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(checkBoxAllowMultipleRequests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxForceShowOfSeries)
                .addGap(18, 18, 18)
                .addComponent(panelAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStart, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                .addContainerGap()));
    }// </editor-fold>                                                                                  
    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonAddPlayer;
    private javax.swing.JButton buttonStart;
    private javax.swing.JCheckBox checkBoxAllowMultipleRequests;
    private javax.swing.JCheckBox checkBoxForceShowOfSeries;
    private javax.swing.JCheckBox checkBoxIsHuman;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPlayerName;
    private javax.swing.JList listPlayerItems;
    private javax.swing.JPanel panelAddPlayer;
    private javax.swing.JPanel panelPlayersList;
    private javax.swing.JTextField textFieldPlayerName;
    // End of variables declaration                   
}
