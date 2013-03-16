package engine;

import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
class GameSettings {

    private boolean repeatTurnWhenSuccessful;
    private boolean revealFlushOnSuccessfulPlay;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
    private LinkedList<LinkedList<String>> availableCardFaces;

    public LinkedList<LinkedList<String>> getAvailableCardFaces() {
        return availableCardFaces;
    }

    public GameSettings() {
        this.repeatTurnWhenSuccessful = true;
        this.revealFlushOnSuccessfulPlay = true;
        this.minNumberOfPlayers = 3;
        this.maxNumberOfPlayers = 6;
        this.availableCardFaces = new LinkedList<>();
    }

    public void addSeriesOfFaces(String[] series) {
        LinkedList<String> facesToAdd = new LinkedList<>();
        for (String face : series) {
            facesToAdd.add(face);
        }

        this.availableCardFaces.add(facesToAdd);
    }

    public boolean isRepeatTurnWhenSuccessful() {
        return repeatTurnWhenSuccessful;
    }

    public void setRepeatTurnWhenSuccessful(boolean repeatTurnWhenSuccessful) {
        this.repeatTurnWhenSuccessful = repeatTurnWhenSuccessful;
    }

    public boolean isRevealFlushOnSuccessfulPlay() {
        return revealFlushOnSuccessfulPlay;
    }

    public void setRevealFlushOnSuccessfulPlay(boolean revealFlush) {
        this.revealFlushOnSuccessfulPlay = revealFlush;
    }

    public int getMinimumNumberOfPlayers() {
        return minNumberOfPlayers;
    }

    public void setMinimumNumberOfPlayers(int minimumNumberOfPlayers) {
        this.minNumberOfPlayers = minimumNumberOfPlayers;
    }

    public int getMaximumNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaximumNumberOfPlayers(int maximumNumberOfPlayers) {
        this.maxNumberOfPlayers = maximumNumberOfPlayers;
    }

    int getInitalHandSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
