package console.humanPlayerInterface;

import com.google.common.collect.HashMultimap;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.IFourPicker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 *
 * @author adamnark
 */
public class HumanFourPicker implements IFourPicker {

    @Override
    public Collection<Card> pickFour(Hand hand) {
        printHandWithIndex(hand);
        System.out.println("Which cards would you like to throw? Seperate card numbers with space and end with return. You can leave this blank to skip.");


        String input = console.utils.InputUtils.readLine();
        LinkedList<Card> four = null;
        boolean ok = false;
        do {
            try {
                four = parseUserInput(input, hand);
                ok = true;
            } catch (BadInputException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!ok);

        return four;
    }

    private void printHandWithIndex(Hand hand) {
        int i = 1;
        for (Card card : hand.getCards()) {
            System.out.print(i + ": ");
            console.utils.GameStatusPrinter.printCard(card);
            i++;
        }
    }

    private LinkedList<Card> parseUserInput(String input, Hand hand) throws BadInputException {

        if (input == null || input.equals("")) {
            return null;
        }
        LinkedList<Card> four = new LinkedList<>();
        Scanner sc = new Scanner(input);
        sc.findInLine("(\\d+) (\\d+) (\\d+) (\\d+)");
        MatchResult result = sc.match();
        for (int i = 1; i <= result.groupCount(); i++) {
            int cardNum = Integer.parseInt(result.group(i)) - 1;

            if (cardNum < 0 || cardNum > hand.getCards().size()) {
                throw new BadInputException("Please enter valid card numbers.");
            }

            four.add(hand.getCards().get(cardNum));
        }

        if (!validateFour(four)) {
            throw new BadInputException("this is not a good four to throw. pick 4 cards that have common serieses or leave blank.");
        }

        return four;
    }

    private boolean validateFour(Collection<Card> cards) {
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
