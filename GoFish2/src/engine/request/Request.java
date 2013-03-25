package engine.request;

import engine.cards.Card;
import engine.players.Player;

/**
 *
 * @author adamnark
 */
public class Request {
    private Player otherPlayer;
    private Card cardIWant;

    public Request(Player otherPlayer, Card cardIWant) {
        this.otherPlayer = otherPlayer;
        this.cardIWant = cardIWant;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Card getCardIWant() {
        return cardIWant;
    }

    public void setCardIWant(Card cardIWant) {
        this.cardIWant = cardIWant;
    }
}
