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
import java.io.Serializable;

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
    
    private Collection<Card> lastCardsThrown;

    public Player() {
        this.hand = new Hand();
        this.isHuman = true;
        this.score = 0;
        this.name = "%default name%";
        this.lastCardsThrown = null;
    }

    public Player(Player other) {
        this.score = 0;
        this.isHuman = other.isHuman;
        this.fourPicker = other.fourPicker;
        this.requestMaker = other.requestMaker;
        this.hand = new Hand(other.hand);
    }

    @Override
    public String toString() {
        return name + "{ hand=" + hand + ", score=" + score + '}';
    }

    public boolean isHuman(){
        return this.isHuman;
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

    public boolean makeMove(LinkedList<Player> otherPlayers, Set<Series> availableSeries) {// throws BadCardRequestException {

        if (!this.isPlaying()) {
            return false;
        }

        Request request = this.requestMaker.makeRequest(this.hand, new ArrayList<>(availableSeries), otherPlayers);

        if (request == null) {
            return false;
        }

        boolean isValidRequest = RequestValidator.validateRequest(request, this.hand);
        if (!isValidRequest) {
            return false;
            //throw new BadCardRequestException();
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
        this.lastCardsThrown = this.fourPicker.pickFour(hand);
        if (this.lastCardsThrown == null) {
            return false;
        }

        for (Card card : this.lastCardsThrown) {
            this.hand.removeCardFromHand(card);
        }
        
        return true;
    }
    
    public Collection<Card> getLastCardsThrown(){
        return this.lastCardsThrown;
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
