package engine;

import engine.Moves.IMover;
import engine.Moves.Move;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Player {
    private String name;
    private LinkedList<Card> hand;
    private IMover mover;
    
    public boolean makeMove(LinkedList<Player> players){
        Move move;
        move = this.mover.makeMove(players);
        return this.DemandCardFromAnotherPlayer(move);
    }
    
    public Player(){
        this.hand = new LinkedList<>();
        this.name = "";
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }

    public void AddCardToHand(Card card){
        if (card == null){
            throw new NullPointerException("card");
        }

        this.hand.add(card);
    }
    
    public void RemoveCardFromHand(Card card){
        if (card == null){
            throw new NullPointerException("card");
        }
        
        if (this.HasCard(card)){
            this.hand.remove(card);
        }
        else {
            throw new IllegalArgumentException("Player can't be asked remove a card that it does not have in its hand.");
        }
    }
    
    public boolean HasCard(Card otherCard){
        if (otherCard == null) {
            throw new NullPointerException("card");
        }
        
        for (Card myCard : this.hand) {
            if (myCard.equals(otherCard)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean DemandCardFromAnotherPlayer(Move move){
        Card card = move.getCardToAsk();
        Player otherPlayer = move.getPlayerToAskFrom();
        if (otherPlayer == null || card == null){
            throw new NullPointerException("card");
        }
        
        if (otherPlayer.HasCard(card)){
            otherPlayer.RemoveCardFromHand(card);
            this.AddCardToHand(card);
            return true;
        }
        
        return false;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null){
            throw new NullPointerException("name");
        }
        
        this.name = name;
    }
}
