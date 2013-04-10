package engine;

import java.io.Serializable;

/**
 *
 * @author adamnark
 */
public class GameSettings {

    private boolean allowMutipleRequests;
    private boolean forceShowOfSeries;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;

    public GameSettings() {
        this.minNumberOfPlayers = 3;
        this.maxNumberOfPlayers = 6;
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
}
