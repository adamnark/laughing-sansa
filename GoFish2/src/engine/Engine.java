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
    private boolean gameStarted;

    public static enum Event {

        TURN_UPDATED,
        SCORE_UPDATED,
        HAND_UPDATED,
        GAME_OVER,
        FAILED_REQUEST,
        SUCCESSFUL_REQUEST,
        FOUR_CARDS_THROWN,
    }

    public Engine() {
        this.players = new ArrayList<>();
        this.eventQueue = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.cardsBySeries = HashMultimap.create();
        this.gameStarted = false;
    }

    private void startGame() {
        this.gameStarted = true;
        initCardMap();
    }

    public void Turn() {
        if (!this.gameStarted){
            startGame();
        }

        boolean cardWasTaken;
        cardWasTaken = getCurrentPlayer().makeMove(getOtherPlayers(), this.cardsBySeries.keySet());
        if (cardWasTaken) {
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SUCCESSFUL_REQUEST);
        } else {
            this.eventQueue.add(Event.FAILED_REQUEST);
        }

        boolean cardsWereThrown = getCurrentPlayer().throwFour();
        if (cardsWereThrown) {
            getCurrentPlayer().increaseScore();
            this.eventQueue.add(Event.HAND_UPDATED);
            this.eventQueue.add(Event.SCORE_UPDATED);
            this.eventQueue.add(Event.FOUR_CARDS_THROWN);
        }

        if (!cardWasTaken || !this.gameSettings.isAllowMutipleRequests()) {
            advanceTurn();
            this.eventQueue.add(Event.TURN_UPDATED);
        }
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
                if (card.isValid()) {
                    count++;
                }
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

    private LinkedList<Player> getOtherPlayers() {
        LinkedList<Player> lst = new LinkedList<>(players);
        lst.remove(getCurrentPlayer());
        return lst;
    }
}
