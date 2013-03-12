package engine;

import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Engine {
    private LinkedList<Player> players;
    private int currentPlayerIndex;
    private LinkedList<Card> ocean;
    private GameSettings gameSettings;
    
    public Engine(){
        this.players = new LinkedList<>();
        this.ocean = new LinkedList<>();
        this.currentPlayerIndex = 0;
    }
    
    public void PlayTurn(){
        Player currentPlayer = this.players.get(currentPlayerIndex);
        boolean goodPlay = currentPlayer.makeMove(this.players, this.gameSettings.getAvailableCardFaces());
        if (!goodPlay || !this.gameSettings.isRepeatTurnWhenSuccessful()) {
            this.AdvanceTurnToNextPlayer();
        }
    }
    
    public LinkedList<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    
    public void AdvanceTurnToNextPlayer(){
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }
    
    public void addCardToOcean(Card card){
    if (card == null)
        throw new NullPointerException("card");
    this.ocean.add(card);
    }
}
