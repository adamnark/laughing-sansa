package engine;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Engine {

    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private GameSettings gameSettings;
    private ArrayList<Event> eventQueue;
    private HashMultimap<Series, Card> cardsBySeries;

    public static enum Event {

        TURN_UPDATED,
        SCORE_UPDATED,
        HAND_UPDATED,
        GAME_OVER,
        FAILED_REQUEST,
        SUCCESSFUL_REQUEST,
    }

    public Engine() {
        this.players = new ArrayList<>();
        this.eventQueue = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.cardsBySeries = HashMultimap.create();
    }

    public void startGame() {
        initCardMap();
    }

    private void initCardMap() {
        for (Player player : players) {
            for (Card card : player.getHand().getCards()) {
                for (Series series : card.getSeries()) {
                    this.cardsBySeries.put(series, card);
                }
            }
        }

    }

    public boolean isGameOver() {
        for (Series series : this.cardsBySeries.keySet()) {
            int count = 0;
            for (Card card : this.cardsBySeries.get(series)) {
                if (card.isValid())
                    count++;
            }
            
            if (count >= 4) {
                return true;
            }
        }

        return false;
    }

    public void advanceTurn() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return this.players.get(currentPlayerIndex);
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public ArrayList<Event> getEventQueue() {
        return eventQueue;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }
}
