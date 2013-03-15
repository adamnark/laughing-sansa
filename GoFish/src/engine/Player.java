package engine;

import engine.cardRequest.ICardRequester;
import engine.cardRequest.CardRequest;
import java.util.LinkedList;

/**
 *
 * @author adamnark
 */
public class Player {
    private String name;
    private LinkedList<Card> hand;
    private ICardRequester cardRequester;
    private int score;

    public Player(){
       this.hand = new LinkedList<>();
       this.name = "";
       this.score = 0;
    }   
    
    public boolean makeMove(LinkedList<Player> players, LinkedList<LinkedList<String>> availableFaces){
        CardRequest move = this.cardRequester.requestCard(players, availableFaces);
        return this.demandCardFromAnotherPlayer(move);
    }
       
    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }

    public void addCardToHand(Card card){
        if (card == null){
            throw new NullPointerException("card");
        }

        this.hand.add(card);
    }
    
    public void removeCardFromHand(Card card){
        if (card == null){
            throw new NullPointerException("card");
        }
        
        if (this.hasCard(card)){
            this.hand.remove(card);
        }
        else {
            throw new IllegalArgumentException("Player can't be asked remove a card that it does not have in its hand.");
        }
    }
    
    public boolean hasCard(Card otherCard){
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
    
    public boolean demandCardFromAnotherPlayer(CardRequest move){
        Card card = move.getCardToAsk();
        Player otherPlayer = move.getPlayerToAskFrom();
        if (otherPlayer == null || card == null){
            throw new NullPointerException("card");
        }
        
        if (otherPlayer.hasCard(card)){
            otherPlayer.removeCardFromHand(card);
            this.addCardToHand(card);
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

    public boolean throwCards() {
        return false;
    }
}
