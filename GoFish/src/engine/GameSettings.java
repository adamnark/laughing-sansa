package engine;

/**
 *
 * @author adamnark
 */
class Settings {
    private boolean repeatTurnWhenSuccessful;
    private boolean revealFlush;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
    
    
    
    public Settings() {
        this.repeatTurnWhenSuccessful = true;
        this.revealFlush = true;
        this.minNumberOfPlayers = 3;
        this.maxNumberOfPlayers = 6;
    }
    
    public boolean isRepeatTurnWhenSuccessful() {
        return repeatTurnWhenSuccessful;
    }

    public void setRepeatTurnWhenSuccessful(boolean repeatTurnWhenSuccessful) {
        this.repeatTurnWhenSuccessful = repeatTurnWhenSuccessful;
    }

    public boolean isRevealFlush() {
        return revealFlush;
    }

    public void setRevealFlush(boolean revealFlush) {
        this.revealFlush = revealFlush;
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
}
