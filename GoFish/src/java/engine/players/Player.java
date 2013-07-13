package engine.players;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.ai.AiFourPicker;
import engine.players.ai.AiRequestMaker;
import engine.players.exceptions.InvalidFourException;
import engine.request.IRequestMaker;
import engine.request.Request;
import engine.request.RequestValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
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

    public Card getCard(String name) {
        for (Card card : hand.getCards()) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
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

    public Player makeMove(LinkedList<Player> otherPlayers, Set<Series> availableSeries) {// throws BadCardRequestException {
        if (!this.isPlaying()) {
            return null;
        }

        Request request = this.requestMaker.makeRequest(this.hand, new ArrayList<>(availableSeries), otherPlayers);
        if (request == null) {
            return null;
        }

        boolean isValidRequest = RequestValidator.validateRequest(request, this.hand);
        if (!isValidRequest) {
            return null;
        }

        Player otherPlayer = request.getOtherPlayer();
        Card victim = otherPlayer.giveUpACard(request.getCardIWant());
        if (victim == null) {
            return null;
        } else {
            this.hand.addCardToHand(victim);
            return otherPlayer;
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

    public boolean throwFour() throws InvalidFourException {
        boolean cardsWereThrown;
        Collection<Card> four = this.fourPicker.pickFour(hand);

        if (!validateFour(four)) {
            throw new InvalidFourException("Must use 4 cards that share a series");
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
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
        } else if (cards.size() != 4) {
            isValid = false;
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
