package engine;

import com.google.common.collect.HashMultimap;
import settings.GameSettings;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Engine {

    private LinkedList<Player> players;
    private int currentPlayerIndex;
    private GameSettings gameSettings;
    private LinkedList<Event> eventQueue;
    private HashMultimap<String, Card> cardsBySeries;
    
    
    public static enum Event {
        TURN_UPDATED,
        SCORE_UPDATED,
        HAND_UPDATED,
        GAME_OVER, 
        FAILED_REQUEST, 
        SUCCESSFUL_REQUEST,
    }

    public Engine() {
        this.players = new LinkedList<>();
        this.eventQueue = new LinkedList<>();
        this.currentPlayerIndex = 0;
        this.cardsBySeries = HashMultimap.create();
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
                return false;
            }
        }

        this.eventQueue.add(Event.GAME_OVER);
        return true;
    }

    public void playTurn()
            throws InvalidMoveException {
        Player currentPlayer = this.players.get(currentPlayerIndex);

        boolean cardWasTaken;

        
        LinkedList<Player> otherPlayers = new LinkedList<>(players);
        otherPlayers.remove(currentPlayer);
        cardWasTaken = currentPlayer.makeMove(otherPlayers, this.gameSettings.getAvailableSerieses());
        if (cardWasTaken) {
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SUCCESSFUL_REQUEST);
        }else{
            this.eventQueue.add(Event.FAILED_REQUEST);
        }
            

        boolean cardsWereThrown = currentPlayer.throwCards();
        if (cardsWereThrown) {
            int score = currentPlayer.getScore();
            currentPlayer.setScore(score + 1);
            
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SCORE_UPDATED);
        }

        if (!cardWasTaken || !this.gameSettings.isAllowMutipleRequests()) {
            this.advanceTurnToNextPlayer();
            this.eventQueue.add(Event.TURN_UPDATED);
        }
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public LinkedList<Event> getEventQueue() {
        return eventQueue;
    }

    public void setEventQueue(LinkedList<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public void advanceTurnToNextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    
    public Player getCurrentPlayer(){
        return this.players.get(currentPlayerIndex);
    }
}
