package engine;

import settings.GameSettings;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class Engine {

    private LinkedList<Player> players;
    private int currentPlayerIndex;
    private GameSettings gameSettings;
    private LinkedList<Event> eventQueue;

    public static enum Event {

        TURN_UPDATED,
        SCORE_UPDATED,
        HAND_UPDATED,
        GAME_OVER,
    }

    public Engine() {
        this.players = new LinkedList<>();
        this.eventQueue = new LinkedList<>();
        this.currentPlayerIndex = 0;
    }

    public boolean checkEndgame() {
        HashMap<String, Integer> hashmap = new HashMap<>();
        LinkedList<Card> availableCards = new LinkedList<>();
        for (Player player : players) {
            availableCards.addAll(player.getHand());
        }

        for (Card card : availableCards) {
            for (String series : card.getSerieses()) {
                hashmap.put(series, 0);
            }
        }

        for (Card card : availableCards) {
            for (String series : card.getSerieses()) {
                int count = hashmap.get(series);
                count++;
                hashmap.put(series, count);
            }
        }

        for (String series : hashmap.keySet()) {
            if (hashmap.get(series) >= 4) {
                this.eventQueue.add(Event.GAME_OVER);
                return true;
            }
        }

        return false;
    }

    public void playTurn()
            throws InvalidMoveException {
        Player currentPlayer = this.players.get(currentPlayerIndex);

        boolean cardWasTaken = false;
        try {
            cardWasTaken = currentPlayer.makeMove(this.players, this.gameSettings.getAvailableSerieses());
        } catch (InvalidMoveException ex) {
            throw ex;
        }

        if (cardWasTaken) {
            this.eventQueue.add(Event.HAND_UPDATED);
        }

        boolean cardsWereThrown = currentPlayer.throwCards();
        if (cardsWereThrown) {
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SCORE_UPDATED);
        }

        if (!cardWasTaken || !this.gameSettings.isAllowMutipleRequests()) {
            this.advanceTurnToNextPlayer();
            this.eventQueue.add(Event.TURN_UPDATED);
        }
    }

    public void advanceTurnToNextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
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
}
