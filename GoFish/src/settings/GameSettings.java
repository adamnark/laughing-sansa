package settings;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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
    private HashSet<String> availableSerieses;
    private int initalHandSize;


    public GameSettings() {
        this.allowMutipleRequests = true;
        this.forceShowOfSeries = true;
        this.minNumberOfPlayers = 3;
        this.maxNumberOfPlayers = 6;
        this.initalHandSize = 5;
        this.availableSerieses = new HashSet<>();
    }

    public void addSeriesesToAvailable(Collection<String> serieses) {
        this.availableSerieses.addAll(serieses);
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

    public int getInitalHandSize() {
        return initalHandSize;
    }

    public void setInitalHandSize(int initalHandSize) {
        this.initalHandSize = initalHandSize;
    }
    
    
}
