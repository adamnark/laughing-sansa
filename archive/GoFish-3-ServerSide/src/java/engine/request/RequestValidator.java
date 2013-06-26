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

    public static void tryValidateRequest(Request request, Hand hand) throws OwnSeriesMissingException, InvalidCardException, MissingPlayerException {
        if (request.getCardIWant() == null) {
            throw new InvalidCardException();
        }
        if (request.getOtherPlayer() == null) {
            throw new MissingPlayerException();
        }

        if (!validateRequest(request, hand)) {
            throw new OwnSeriesMissingException();
        }

        if (!isCardValid(request, hand)) {
            throw new InvalidCardException();
        }
    }

    private static boolean isCardValid(Request request, Hand hand) {
        if (hand.getCards().isEmpty()) {
            return false;
        }
        int numOfSeriesInCard = request.getCardIWant().getSeries().size();
        int numOfSeriesInGeneral = hand.getCards().get(0).getSeries().size();

        return numOfSeriesInCard == numOfSeriesInGeneral;
    }

    public static class OwnSeriesMissingException extends Exception {

        public OwnSeriesMissingException() {
        }
    }

    public static class InvalidCardException extends Exception {

        public InvalidCardException() {
        }
    }

    public static class MissingPlayerException extends Exception {

        public MissingPlayerException() {
        }
    }
}
