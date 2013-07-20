package engine;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import engine.players.exceptions.InvalidFourException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class Engine {

    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private GameSettings gameSettings;
    private LinkedList<String> eventQueue;
    private HashMultimap<Series, Card> cardsBySeries;
    private boolean isGameStarted;
    private Collection<Card> lastCardsThrown;
    public static final String EVENT_GAME_OVER = "Game Over";

    public Engine() {
        this.players = new ArrayList<>();
        this.eventQueue = new LinkedList<>();
        this.currentPlayerIndex = 0;
        this.cardsBySeries = HashMultimap.create();
        this.isGameStarted = false;
    }

    public void currentPlayerThrowFour() throws InvalidFourException {
        boolean cardsWereThrown = getCurrentPlayer().throwFour();
        if (cardsWereThrown) {
            getCurrentPlayer().increaseScore();
            setLastCardsThrown(getCurrentPlayer().getLastCardsThrown());
            this.eventQueue.add("4 cards were thrown by " + getCurrentPlayer().getName() + "!");
        }

        if (!getCurrentPlayer().isPlaying()) {
            this.eventQueue.add(getCurrentPlayer().getName() + " has run out of cards");
        }
    }

    public Collection<Card> getLastCardsThrown() {
        return this.lastCardsThrown;
    }

    public List<Series> getAvailableSeries() {
        List<Series> lst = new LinkedList<>();
        for (Series series : this.cardsBySeries.keySet()) {
            lst.add(series);
        }
        return lst;
    }

    public boolean currentPlayerMakeRequest() {
        Player victim;
        victim = getCurrentPlayer().makeMove(getOtherPlayers(), this.cardsBySeries.keySet());
        if (victim != null) {
            return true;
        } else {
            this.eventQueue.add(getCurrentPlayer().getName() + " made a bad guess..");
            return false;
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

    private void setLastCardsThrown(Collection<Card> lastCardsThrown) {
        this.lastCardsThrown = lastCardsThrown;
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

        if (isNoPlayersLeft()) {
            return true;
        }

        if (isOnePlayerLeft()) {
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
        if (this.isGameOver()) {
            this.eventQueue.add(EVENT_GAME_OVER);
        } else {
            do {
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
            } while (getCurrentPlayer().getHand().getCards().isEmpty());
        }
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

    public LinkedList<String> getEventQueue() {
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

    private boolean isNoPlayersLeft() {
        for (Player player : players) {
            if (player.isPlaying()) {
                return false;
            }
        }
        return true;
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (name.equals(player.getName())) {
                return player;
            }
        }

        return null;
    }

    public void removerPlayer(Player player) {
        if (player.equals(this.getCurrentPlayer())){
            advanceTurn();
        }
        
        this.players.remove(player);
        this.eventQueue.add(player.getName() + " has left the game!");
        
    }

    public Card getCardByName(String string) {
        Card card = null;
        for (Player player : players) {
            card = player.getCard(string);
            if (card != null) {
                break;
            }
        }
        return card;
    }

    public void endOfTurn() {
        advanceTurn();
        if (!this.getCurrentPlayer().isHuman()) {
            playAITurn();
        }
    }

    private void playAITurn() {
        try {
            currentPlayerMakeRequest();
            currentPlayerThrowFour();
            endOfTurn();
        } catch (InvalidFourException ex) {
        }
    }

//    public static enum Event {
//
//        FAILED_REQUEST,
//        SUCCESSFUL_REQUEST,
//        FOUR_CARDS_THROWN,
//        FOUR_CARDS_NOT_THROWN,
//        PLAYER_OUT_OF_CARDS,
//        GAME_OVER,
//    }
}
