package engine;

import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public abstract class PlayerInfo {
    private String name;
    private LinkedList<Card> hand;
    
    
    public PlayerInfo(){
        hand = new LinkedList<>();
        name = "";
    }
    
    public void AddCardToHand(Card card){
        if (card == null){
            throw new NullPointerException("card");
        }

        hand.add(card);
    }
    
    public void RemoveCardFromHand(Card card)
    {
        if (card == null){
            throw new NullPointerException("card");
        }
        
        if (this.HasCard(card)){
            hand.remove(card);
        }
        else {
            throw new IllegalArgumentException("Player can't be asked remove a card that it does not have in its hand.");
        }
    }
    
    public boolean HasCard(Card card){
        if (card == null) {
            throw new NullPointerException("card");
        }
        
        for (Card otherCard : hand) {
            if (otherCard.equals(card)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean DemandCardFromAnotherPlayer(PlayerInfo otherPlayer, Card card){
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
        if (name == null){
            return "Rodriguez";
        }
        
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
