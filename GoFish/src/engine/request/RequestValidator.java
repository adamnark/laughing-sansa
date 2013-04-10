package engine.request;

import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;

/**
 *
 * @author adamnark
 */
public class RequestValidator {

    public static boolean validateRequest(Request request, Hand hand) {
        for (Card card : hand.getCards()) {
            for (Series series : card.getSeries()) {
                if (request.getCardIWant().isInSeries(series)) {
                    return true;
                }
            }
        }

        return false;
    }
}
