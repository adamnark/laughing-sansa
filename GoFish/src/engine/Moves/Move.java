package engine.Moves;

import engine.Player;
import engine.Card;

/**
 *
 * @author adamnark
 */
public class Move {
    private Player playerToAskFrom;
    private Card cardToAsk;

    public Move(Player playerToAskFrom, Card cardToAsk) {
        this.playerToAskFrom = playerToAskFrom;
        this.cardToAsk = cardToAsk;
    }

    public Player getPlayerToAskFrom() {
        return playerToAskFrom;
    }

    public void setPlayerToAskFrom(Player playerToAskFrom) {
        this.playerToAskFrom = playerToAskFrom;
    }

    public Card getCardToAsk() {
        return cardToAsk;
    }

    public void setCardToAsk(Card cardToAsk) {
        this.cardToAsk = cardToAsk;
    }

}
