package engine;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class Engine {

    private LinkedList<Player> players;
    private int currentPlayerIndex;
    private LinkedList<Card> ocean;
    private GameSettings gameSettings;

    public Engine() {
        this.players = new LinkedList<>();
        this.ocean = new LinkedList<>();
        this.currentPlayerIndex = 0;
    }

    public void playTurn() {
        Player currentPlayer = this.players.get(currentPlayerIndex);
        boolean cardWasTaken = currentPlayer.makeMove(this.players, this.gameSettings.getAvailableCardFaces());


        if (!cardWasTaken || !this.gameSettings.isRepeatTurnWhenSuccessful()) {
            this.advanceTurnToNextPlayer();
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void advanceTurnToNextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }

    public void addCardToOcean(Card card) {
        if (card == null) {
            throw new NullPointerException("card");
        }
        this.ocean.add(card);
    }
}
