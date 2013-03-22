package settings;

import java.util.Arrays;
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
        facesToAdd.addAll(Arrays.asList(series));
        this.availableSerieses.add(facesToAdd);
    }

    public boolean isAllowMutipleRequests() {
        return allowMutipleRequests;
    }

    public void setAllowMutipleRequests(boolean allowMutipleRequests) {
        this.allowMutipleRequests = allowMutipleRequests;
    }

    public boolean isForceShowOfSeries() {
        return forceShowOfSeries;
    }

    public void setForceShowOfSeries(boolean forceShowOfSeries) {
        this.forceShowOfSeries = forceShowOfSeries;
    }

    public int getMinNumberOfPlayers() {
        return minNumberOfPlayers;
    }

    public void setMinNumberOfPlayers(int minNumberOfPlayers) {
        this.minNumberOfPlayers = minNumberOfPlayers;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public LinkedList<LinkedList<String>> getAvailableSerieses() {
        return availableSerieses;
    }

    public void setAvailableSerieses(LinkedList<LinkedList<String>> availableSerieses) {
        this.availableSerieses = availableSerieses;
    }

    public int getInitalHandSize() {
        return initalHandSize;
    }

    public void setInitalHandSize(int initalHandSize) {
        this.initalHandSize = initalHandSize;
    }
    
    
}
