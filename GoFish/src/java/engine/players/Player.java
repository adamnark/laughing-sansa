package engine.players;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.ai.AiFourPicker;
import engine.players.ai.AiRequestMaker;
import engine.players.exceptions.InvalidFourRuntimeException;
import engine.request.IRequestMaker;
import engine.request.Request;
import engine.request.RequestValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

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
        this.name = other.name;
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

    public boolean isHuman() {
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
        return this.hand;
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

        Card victim = request.getOtherPlayer().giveUpACard(request.getCardIWant());
        if (victim == null) {
            return false;
        } else {
            this.hand.addCardToHand(victim);
            return true;
        }
    }

    private Card giveUpACard(Card requestedCard) {
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
        boolean cardsWereThrown;
        Collection<Card> four = this.fourPicker.pickFour(hand);

        if (!validateFour(four)) {
            throw new InvalidFourRuntimeException();
        } else {
            this.lastCardsThrown = four;//this.fourPicker.pickFour(hand);
            if (this.lastCardsThrown == null) {
                cardsWereThrown = false;
            } else {
                for (Card card : this.lastCardsThrown) {
                    this.hand.removeCardFromHand(card);
                }

                cardsWereThrown = true;
            }
        }

        return cardsWereThrown;
    }

    public Collection<Card> getLastCardsThrown() {
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

    private boolean validateFour(Collection<Card> cards) {
        boolean isValid;
        if (cards == null) {
            isValid = true;
        } else {
            HashMultimap<Series, Card> map = HashMultimap.create();
            for (Card card : cards) {
                for (Series series : card.getSeries()) {
                    map.put(series, card);
                }
            }

            isValid = false;
            for (Series series : map.keySet()) {
                int count = map.get(series).size();
                if (count == 4) {
                    isValid = true;
                    break;
                }
            }
        }

        return isValid;
    }
}
