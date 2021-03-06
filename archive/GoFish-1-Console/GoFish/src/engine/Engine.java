package engine;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Engine {

    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private GameSettings gameSettings;
    private LinkedList<Event> eventQueue;
    private HashMultimap<Series, Card> cardsBySeries;
    private boolean isGameStarted;

    public void currentPlayerThrowFour() {
        boolean cardsWereThrown = getCurrentPlayer().throwFour();
        if (cardsWereThrown) {
            getCurrentPlayer().increaseScore();
            this.eventQueue.add(Event.FOUR_CARDS_THROWN);
        } else {
            this.eventQueue.add(Event.FOUR_CARDS_NOT_THROWN);
        }

        if (!getCurrentPlayer().isPlaying()) {
            this.eventQueue.add(Event.PLAYER_OUT_OF_CARDS);
        }

    }

    public void currentPlayerMakeRequest() {
        boolean cardWasTaken;
        cardWasTaken = getCurrentPlayer().makeMove(getOtherPlayers(), this.cardsBySeries.keySet());
        if (cardWasTaken) {
            this.eventQueue.add(Event.SUCCESSFUL_REQUEST);
        } else {
            this.eventQueue.add(Event.FAILED_REQUEST);
        }
    }

    private boolean isOnePlayerLeft() {
        int count = 0;
        for (Player player : players) {
            if (player.isPlaying()) {
                count++;
            }
        }
        return count == 1;
    }

    public Player getWinner() {
        Player winner = getCurrentPlayer();
        for (Player player : players) {
            if (player.getScore() >= winner.getScore()) {
                winner = player;
            }
        }
        
        return winner;
    }

    public static enum Event {

        FAILED_REQUEST,
        SUCCESSFUL_REQUEST,
        FOUR_CARDS_THROWN,
        FOUR_CARDS_NOT_THROWN,
        PLAYER_OUT_OF_CARDS,
    }

    public Engine() {
        this.players = new ArrayList<>();
        this.eventQueue = new LinkedList<>();
        this.currentPlayerIndex = 0;
        this.cardsBySeries = HashMultimap.create();
        this.isGameStarted = false;
    }

    public void startGame() {
        this.isGameStarted = true;
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
        if (!this.isGameStarted) {
            return false;
        }

        boolean onePlayerLeft = isOnePlayerLeft();
        if (onePlayerLeft) {
            return true;
        }
        for (Series series : this.cardsBySeries.keySet()) {
            int count = 0;
            for (Card card : this.cardsBySeries.get(series)) {
                if (card.isValid()) {
                    count++;
                }
            }
            if (count >= 4) {
                return false;
            }
        }

        return true;
    }

    public void advanceTurn() {
        do {
            this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        } while (getCurrentPlayer().getHand().getCards().isEmpty());
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

    public LinkedList<Event> getEventQueue() {
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

    public void addPlayers(Collection<Player> newPlayers) {
        for (Player player : newPlayers) {
            addPlayer(player);
        }
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }
}
