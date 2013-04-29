package swing.playerinterface;

import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import engine.request.IRequestMaker;
import engine.request.Request;
import java.awt.Dialog;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author adamnark
 */
public class SwingRequestMaker implements IRequestMaker {

    private JDialog jDialog;
    private JPanelRequest jPanelRequest;

    @Override
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        initJPanelRequest(hand, availableSeries, otherPlayers);
        initDialog();
        jDialog.setVisible(true);
        System.out.println(this.jPanelRequest.getSelectedPlayerName());

        return new Request(null, null);
    }

    private void initDialog() {
        this.jDialog = new JDialog(null, "title", Dialog.ModalityType.APPLICATION_MODAL);
        this.jDialog.setPreferredSize(new Dimension(400, 400));
        this.jDialog.setMinimumSize(new Dimension(400, 400));
        this.jDialog.setMaximumSize(new Dimension(400, 400));
        this.jDialog.setContentPane(this.jPanelRequest);
        this.jDialog.setLocationRelativeTo(null);

    }

    private void initJPanelRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        this.jPanelRequest = new JPanelRequest();
        this.jPanelRequest.setHand(hand);
        this.jPanelRequest.setAvailableSeries(availableSeries);
        this.jPanelRequest.setListOfPlayers(otherPlayers);
        this.jPanelRequest.addPropertyChangeListener(JPanelRequest.EVENT_DONE, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                jDialog.setVisible(false);
            }
        });

    }
}
