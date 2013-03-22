package settings;

import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class GameSettings {

    private boolean allowMutipleRequests;
    private boolean forceShowOfSeries;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
    private LinkedList<LinkedList<String>> availableSerieses;
    private int initalHandSize;

    public LinkedList<LinkedList<String>> getAvailableCardFaces() {
        return availableSerieses;
    }

    public GameSettings() {
        this.allowMutipleRequests = true;
        this.forceShowOfSeries = true;
        this.minNumberOfPlayers = 3;
        this.maxNumberOfPlayers = 6;
        this.initalHandSize = 5;
        this.availableSerieses = new LinkedList<>();
    }

    public void addSeriesOfFaces(String[] series) {
        LinkedList<String> facesToAdd = new LinkedList<>();
        for (String face : series) {
            facesToAdd.add(face);
        }

        this.availableSerieses.add(facesToAdd);
    }

    public boolean isRepeatTurnWhenSuccessful() {
        return allowMutipleRequests;
    }

    public void setRepeatTurnWhenSuccessful(boolean repeatTurnWhenSuccessful) {
        this.allowMutipleRequests = repeatTurnWhenSuccessful;
    }

    public boolean isRevealFlushOnSuccessfulPlay() {
        return forceShowOfSeries;
    }

    public void setRevealFlushOnSuccessfulPlay(boolean revealFlush) {
        this.forceShowOfSeries = revealFlush;
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

    public int getInitalHandSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
