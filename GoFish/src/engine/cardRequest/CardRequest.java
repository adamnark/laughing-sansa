package engine.cardRequest;

import engine.Player;
import engine.Card;

/**
 *
 * @author adamnark
 */
public class CardRequest {

    private Player playerToAskFrom;
    private Card cardToAsk;

    public CardRequest(Player playerToAskFrom, Card cardToAsk) {
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
