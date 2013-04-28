package engine;

import engine.players.Player;

/**
 *
 * @author adam
 */
public class Validator {

    private static final int MINIMUM_NUMBER_OF_CARDS = 12;
    private static final int MINIMUM_NUMBER_OF_PLAYERS = 3;
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;
    Engine engine;

    public Validator(Engine engine) {
        this.engine = engine;
    }

    public boolean validateEngineState() {
        if (countOfCards() < MINIMUM_NUMBER_OF_CARDS) {
            return false;
        }

        if (countOfPlayers() < MINIMUM_NUMBER_OF_PLAYERS
                || countOfPlayers() > MAXIMUM_NUMBER_OF_PLAYERS) {
            return false;
        }

        if (!isDistinctPlayerNames()) {
            return false;
        }

        return true;
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
        for (Player player1 : engine.getPlayers()) {
            for (Player player2 : engine.getPlayers()) {
                if (player1 != player2
                        && player1.getName().equals(player2.getName())) {
                    return false;
                }
            }
        }
        return true;
    }
}
