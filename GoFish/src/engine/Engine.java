package engine;

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
    private LinkedList<Card> ocean;
    private GameSettings gameSettings;
    private LinkedList<Event> eventQueue;

    private Card popCardFromOcean() {
        Card c = this.ocean.pop();
        return c;
    }

    public static enum Event {

        TURN_MOVED,
        SCORE_UPDATED,
        HAND_UPDATED,
        GAME_OVER,
    }

    public Engine() {
        this.players = new LinkedList<>();
        this.ocean = new LinkedList<>();
        this.eventQueue = new LinkedList<>();
        this.currentPlayerIndex = 0;

    }

    public boolean checkEndgame() {
        HashMap<String, Integer> hashmap = new HashMap<>();
        LinkedList<Card> availableCards = new LinkedList<>(ocean);
        for (Player player : players) {
            availableCards.addAll(player.getHand());
        }

        for (Card card : availableCards) {
            for (String face : card.getFaces()) {
                hashmap.put(face, 0);
            }
        }

        for (Card card : availableCards) {
            for (String face : card.getFaces()) {
                int count = hashmap.get(face);
                count++;
                hashmap.put(face, count);
            }
        }

        for (String face : hashmap.keySet()) {
            if (hashmap.get(face) >= 4) {
                this.eventQueue.add(Event.GAME_OVER);
                return true;
            }
        }

        return false;
    }

    public void playTurn() {
        Player currentPlayer = this.players.get(currentPlayerIndex);
        boolean cardWasTaken = currentPlayer.makeMove(this.players, this.gameSettings.getAvailableCardFaces());
        if (cardWasTaken) {
            this.eventQueue.add(Event.HAND_UPDATED);
        }

        boolean cardsWereThrown = currentPlayer.throwCards();
        if (cardsWereThrown) {
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SCORE_UPDATED);
        } else {
            Card c = this.popCardFromOcean();
            currentPlayer.addCardToHand(c);
        }


        if (!cardWasTaken || !this.gameSettings.isRepeatTurnWhenSuccessful()) {
            this.advanceTurnToNextPlayer();
            this.eventQueue.add(Event.TURN_MOVED);
        }
    }

    public void advanceTurnToNextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }

    public void fillOcean(List<Card> cards) {
        this.ocean = new LinkedList<>(cards);
        Collections.shuffle(this.ocean);
    }

    public void dealCards() {
        int numOfCardsToDeal = this.gameSettings.getInitalHandSize();
        for (Player player : players) {
            for (int i = 0; i < numOfCardsToDeal; i++) {
                player.addCardToHand(this.popCardFromOcean());
            }
        }

        this.eventQueue.add(Event.HAND_UPDATED);
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
