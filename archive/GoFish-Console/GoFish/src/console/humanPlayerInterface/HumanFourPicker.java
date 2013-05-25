package console.humanPlayerInterface;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.IFourPicker;
import java.util.Collection;
import java.util.LinkedList;


/**
 *
 * @author adamnark
 */
public class HumanFourPicker implements IFourPicker {

    @Override
    public Collection<Card> pickFour(Hand hand) {

        System.out.println("Which cards would you like to throw? enter 0 to skip at any time.");
        printHandWithIndex(hand);
        LinkedList<Card> four;

        boolean ok = false;
        do {
            four = pickCardsFromHand(hand);
            ok = validateFour(four);
            if (!ok) {
                System.out.println("This is not a good four to throw.");
            }
        } while (!ok);

        if (four != null) {
            System.out.println("ok, throwing the following cards:");
            console.utils.GameStatusPrinter.printCards(four);
        }else{
            System.out.println("not throwing any cards...");
        }

        return four;
    }

    private void printHandWithIndex(Hand hand) {
        int i = 1;
        for (Card card : hand.getCards()) {
            System.out.print(i + ": ");
            console.utils.GameStatusPrinter.printCard(card);
            System.out.println("");
            i++;
        }
    }

    private LinkedList<Card> pickCardsFromHand(Hand hand) {
        LinkedList<Card> lst = new LinkedList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.print("pick card #" + i + ": ");
            int cardNumber = console.utils.InputUtils.readInteger(0, hand.getCards().size());
            if (cardNumber == 0) {
                return null;
            }
            lst.add(hand.getCards().get(cardNumber - 1));

        }

        return lst;

    }

//    private LinkedList<Card> parseUserInput(String input, Hand hand) throws BadInputException {
//        if (input == null || input.equals("")) {
//            return null;
//        }
//        LinkedList<Card> four = new LinkedList<>();
//        Scanner sc = new Scanner(input);
//        sc.findInLine("(\\d+) (\\d+) (\\d+) (\\d+)");
//        MatchResult result;
//        try {
//            result = sc.match();
//        } catch (IllegalStateException ex) {
//            throw new BadInputException("I need four integers for this to work. seperate with spaces.");
//        }
//        for (int i = 1; i <= result.groupCount(); i++) {
//            int cardNum;
//            try {
//                cardNum = Integer.parseInt(result.group(i)) - 1;
//            } catch (java.lang.NumberFormatException ex) {
//                throw new BadInputException("what are you doing? just enter four little numbers. with spcaces. please.");
//            }
//
//            if (cardNum < 0 || cardNum > hand.getCards().size()) {
//                throw new BadInputException("Please enter valid card numbers. you can do it. just a little effort.");
//            }
//
//            four.add(hand.getCards().get(cardNum));
//        }
//
//        if (!validateFour(four)) {
//            throw new BadInputException("this is NOT a good four to throw. pick 4 cards that have common serieses or leave blank. sigh.");
//        }
//
//        return four;
//    }

    private boolean validateFour(Collection<Card> cards) {
        if (cards == null) {
            return true;
        }
        HashMultimap<Series, Card> map = HashMultimap.create();

        for (Card card : cards) {
            for (Series series : card.getSeries()) {
                map.put(series, card);
            }
        }

        for (Series series : map.keySet()) {
            int count = map.get(series).size();
            if (count == 4) {
                return true;
            }
        }

        return false;
    }

    private static class BadInputException extends Exception {

        private String message;

        public BadInputException() {
        }

        private BadInputException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
