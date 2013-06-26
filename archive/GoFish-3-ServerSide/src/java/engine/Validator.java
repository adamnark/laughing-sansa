package engine;

import engine.cards.Card;
import engine.players.Player;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adam
 */
public class Validator {

    private static final int MINIMUM_NUMBER_OF_CARDS = 28;
    private static final int MINIMUM_NUMBER_OF_PLAYERS = 3;
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;
    Engine engine;
    private List<String> errors;

    public Validator(Engine engine) {
        this.engine = engine;
        this.errors = new LinkedList<>();
    }

    public boolean validateEngineState() {
        boolean isValid = true;

        if (countOfCards() < MINIMUM_NUMBER_OF_CARDS) {
            this.errors.add("Not enough cards. "
                    + MINIMUM_NUMBER_OF_CARDS
                    + " cards are required to play.");
            isValid = false;
        }

        if (countOfPlayers() < MINIMUM_NUMBER_OF_PLAYERS
                || countOfPlayers() > MAXIMUM_NUMBER_OF_PLAYERS) {
            this.errors.add("number of players should be between "
                    + MINIMUM_NUMBER_OF_PLAYERS
                    + " and "
                    + MAXIMUM_NUMBER_OF_PLAYERS
                    + ".");
            isValid = false;
        } else if (!isDistinctPlayerNames()) {
            isValid = false;
        } else if (!isDistinctCards()) {
            isValid = false;
        } else if (!isHumanPlayerExist()) {
            this.errors.add("There must be at least one human player.");
            isValid = false;
        } else if (!uniformNumbeOfSeries()) {
            this.errors.add("All cards must have the same number of serieses.");
            isValid = false;
        }

        return isValid;
    }

    private boolean isHumanPlayerExist() {
        boolean isHumanPlaying = false;
        for (Player player : engine.getPlayers()) {
            if (player.isHuman()) {
                isHumanPlaying = true;
            }
        }

        return isHumanPlaying;
    }

    private int countOfCards() {
        int count = 0;
        for (Player player : engine.getPlayers()) {
            count += player.getHand().getCards().size();
        }

        return count;
    }

    private int countOfPlayers() {
        return engine.getPlayers().size();
    }

    private boolean isDistinctPlayerNames() {
        boolean valid = true;
        for (Player player1 : engine.getPlayers()) {
            for (Player player2 : engine.getPlayers()) {
                if (player1 != player2 && player1.getName().equals(player2.getName())) {
                    this.errors.add("Player name "
                            + player1.getName()
                            + " repeats.");
                    return false;
                }
            }
        }

        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    private boolean isDistinctCards() {
        boolean valid = true;
        List<Card> list = new LinkedList<>();
        roundUpCardsToCollection(list);
        for (Card card1 : list) {
            for (Card card2 : list) {
                if (card1 != card2 && card1.getName().equals(card2.getName())) {
                    this.errors.add("The card "
                            + card1.getName()
                            + " is duplicated. Card names cannot repeat.");
                    return false;
                }
            }
        }

        return valid;
    }

    private void roundUpCardsToCollection(Collection<Card> clctn) {
        for (Player player : this.engine.getPlayers()) {
            clctn.addAll(player.getHand().getCards());
        }
    }

    private boolean uniformNumbeOfSeries() {
        int numOfSeries = this.engine.getPlayers().get(0).getHand().getCards().get(0).getSeries().size();
        List<Card> list = new LinkedList<>();
        roundUpCardsToCollection(list);
        for (Card card : list) {
            if (card.getSeries().size() != numOfSeries) {
                return false;
            }
        }
        return true;
    }
}
