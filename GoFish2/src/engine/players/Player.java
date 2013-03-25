package engine.players;

import engine.request.IRequestMaker;
import engine.request.Request;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.ai.AiFourPicker;
import engine.players.ai.AiRequestMaker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import engine.request.RequestValidator;

/**
 *
 * @author adamnark
 */
public class Player {

    private String name;
    private Hand hand;
    private int score;
    private IFourPicker fourPicker;
    private IRequestMaker requestMaker;
    private boolean isHuman;

    public Player() {
        this.hand = new Hand();
        this.isHuman = true;
        this.score = 0;
        this.name = "%default name%";
    }

    @Override
    public String toString() {
        return name + "{ hand=" + hand + ", score=" + score + '}';
    }
    
    public static Player createAIPlayer() {
        Player player = new Player();
        player.isHuman = false;
        player.fourPicker = new AiFourPicker();
        player.requestMaker = new AiRequestMaker();

        return player;
    }

    public Player(IFourPicker fourPicker, IRequestMaker requestMaker) {
        this();
        this.fourPicker = fourPicker;
        this.requestMaker = requestMaker;
    }
    
    

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public IFourPicker getFourPicker() {
        return fourPicker;
    }

    public void setFourPicker(IFourPicker fourPicker) {
        this.fourPicker = fourPicker;
    }

    public IRequestMaker getRequestMaker() {
        return requestMaker;
    }

    public void setRequestMaker(IRequestMaker requestMaker) {
        this.requestMaker = requestMaker;
    }

    public boolean makeMove(LinkedList<Player> otherPlayers, Set<Series> availableSeries)
            throws BadCardRequestException {
        
        if (!this.isPlaying()){
            return false;
        }
        
        Request request = this.requestMaker.makeRequest(this.hand, new ArrayList<>(availableSeries), otherPlayers);

        if (request == null) {
            return false;
        }
        
        boolean isValidRequest = RequestValidator.validateRequest(request, this.hand);
        if (!isValidRequest) {
            throw new BadCardRequestException();
        }


        Card victim = request.getOtherPlayer().GiveUpACard(request.getCardIWant());
        if (victim == null) {
            return false;
        } else {
            this.hand.addCardToHand(victim);
            return true;
        }
    }

    private Card GiveUpACard(Card requestedCard) {
        Card victim = null;
        for (Card card : this.hand.getCards()) {
            if (card.equals(requestedCard)) {
                victim = card;
                break;
            }
        }
        if (victim != null) {
            this.hand.removeCardFromHand(victim);
        }
        return victim;
    }

    public boolean throwFour() {
        Collection<Card> fourCards = this.fourPicker.pickFour(hand);
        if (fourCards == null) {
            return false;
        }

        for (Card card : fourCards) {
            this.hand.removeCardFromHand(card);
        }

        return true;
    }

    public void increaseScore() {
        this.score++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPlaying() {
        return !this.hand.getCards().isEmpty();
    }
}
