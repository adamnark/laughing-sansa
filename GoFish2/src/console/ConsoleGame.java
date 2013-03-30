
package console;

import engine.Engine;
import console.utils.GameStatusPrinter;
import console.utils.InputUtils;
import static engine.Engine.Event.*;
import java.util.LinkedList;

/**
 *
 * @author adam
 */
public class ConsoleGame {

    private Engine engine;
    private boolean hasPlayerThrownFour = false;
    private boolean hasPlayerRequested = false;

    public ConsoleGame(Engine engine) {
        this.engine = engine;
    }

    public void PlayGame() throws Exception {
        engine.startGame();
        do {
            System.out.println(engine.getCurrentPlayer().getName() + " is playing...");
            if (engine.getCurrentPlayer().isHuman()) {
                doPlayerActionForHuman();
            } else {
                doPlayerActionForComputer();
            }
            InputUtils.pressEnterToContinue();
            GameStatusPrinter.printGameStatus(engine);
            handleEventQueue();
            InputUtils.pressEnterToContinue();
            checkAndAdvanceTurn();
        } while (!engine.isGameOver());
        System.out.println("Game over!");
    }

    private void doPlayerActionForHuman() throws Exception {
        GameStatusPrinter.printGameStatus(engine);
        askPlayerForAction();
    }

    private void doPlayerActionForComputer() {
        if (!hasPlayerThrownFour) {
            this.engine.currentPlayerThrowFour();
        } else if (!hasPlayerRequested) {

            this.engine.currentPlayerMakeRequest();
        }
    }

    private void handleEventQueue() {
        LinkedList<Engine.Event> queue = engine.getEventQueue();
        while (!queue.isEmpty()) {
            Engine.Event event = queue.pop();
            switch (event) {
                case SUCCESSFUL_REQUEST:
                    handleSuccessfulRequest();
                    break;

                case FOUR_CARDS_THROWN:
                    handleFourCardsThrown();
                    break;

                case FAILED_REQUEST:
                    handleFailedRequest();
                    break;

                case FOUR_CARDS_NOT_THROWN:
                    handleFourCardsNotThrown();
                    break;

                default:
                    throw new AssertionError();
            }
        }
    }

    private void checkAndAdvanceTurn() {
        if (this.hasPlayerRequested && this.hasPlayerThrownFour) {
            this.hasPlayerRequested = false;
            this.hasPlayerThrownFour = false;
            this.engine.advanceTurn();
        }
    }

    private void askPlayerForAction() throws Exception {
        if (this.hasPlayerRequested && this.hasPlayerThrownFour) {
            throw new Exception("Player has no actions!");
        }
        System.out.println("What would you like to do?");
        LinkedList<InputUtils.MenuOption> options = new LinkedList<>();

        if (!hasPlayerThrownFour) {
            InputUtils.IAction action = new InputUtils.IAction() {
                @Override
                public void action() {
                    engine.currentPlayerThrowFour();
                }
            };
            options.add(new InputUtils.MenuOption("Throw four cards", action));
        }

        if (!hasPlayerRequested) {
            InputUtils.IAction action = new InputUtils.IAction() {
                @Override
                public void action() {
                    engine.currentPlayerMakeRequest();
                }
            };

            options.add(new InputUtils.MenuOption("Request a card from another player", action));
        }

        int optionIndex = InputUtils.readOptionFromMenu(options);
        options.get(optionIndex).getAction().action();
    }

    private void handleSuccessfulRequest() {
        System.out.println(engine.getCurrentPlayer().getName() + " has made a successful request! w-o-w.");
        if (!engine.getGameSettings().isAllowMutipleRequests()) {
            this.hasPlayerRequested = true;
        } else {
            System.out.println(engine.getCurrentPlayer().getName() + " gets another request!");
            this.hasPlayerRequested = false;
        }
    }

    private void handleFourCardsThrown() {
        this.hasPlayerThrownFour = true;
        System.out.println(engine.getCurrentPlayer().getName() + " has thrown four cards!");
        if (engine.getGameSettings().isForceShowOfSeries()) {
            System.out.println("the cards " + engine.getCurrentPlayer().getName() + " has thrown are:");
            GameStatusPrinter.printCards(engine.getCurrentPlayer().getLastCardsThrown());
        } else {
            System.out.println(engine.getCurrentPlayer().getName() + "doesn't have to show them to you!");
        }
    }

    private void handleFailedRequest() {
        System.out.println(engine.getCurrentPlayer().getName() + "'s request has failed!");
        this.hasPlayerRequested = true;
    }

    private void handleFourCardsNotThrown() {
        System.out.println(engine.getCurrentPlayer().getName() + " couldn't throw four cards!");
        this.hasPlayerThrownFour = true;
    }
}