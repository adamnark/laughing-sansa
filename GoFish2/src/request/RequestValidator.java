package request;

import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;

/**
 *
 * @author adamnark
 */
public class RequestValidator {

    public static boolean validateRequest(Request request, Player player) {
        for (Card card : player.getHand().getCards()) {
            for (Series series : card.getSeries()) {
                if (request.getCardIWant().isInSeries(series)) {
                    return true;
                }
            }
        }

        return false;
    }
}
