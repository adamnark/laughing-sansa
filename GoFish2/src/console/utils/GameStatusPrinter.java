package console.utils;

import engine.Engine;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import java.util.List;

/**
 *
 * @author adamnark
 */
public class GameStatusPrinter {

    public static void printGameStatus(Engine engine) {
        printSeperator();
        printPlayers(engine.getPlayers());
        printCurrentPlayer(engine);
        System.out.println("");
    }

    private static void printSeperator() {
        String line80 = "--------------------------------------------------------------------------------";
        System.out.print("\n" + line80 + "\n");
    }

    public static void printPlayers(List<Player> players) {
        int i = 1;
        for (Player player : players) {
            System.out.print("\t" + i + ": ");
            printPlayer(player);
            System.out.print("\n");
            i++;
        }
    }

    public static void printPlayer(Player player) {
        System.out.print(player.getName() + "/" + player.getScore());
    }

    public static void printCurrentPlayer(Engine engine) {
        System.out.print("Current Player: ");
        printPlayer(engine.getCurrentPlayer());
        System.out.print("\n\t");
        printHand(engine.getCurrentPlayer().getHand());
    }

    public static void printHand(Hand hand) {
        System.out.print("{\n\t\t");
        for (Card card : hand.getCards()) {
            printCard(card);
            System.out.print("\n\t\t");
        }
        System.out.print("\b}");
    }

    public static void printCard(Card card) {
        System.out.print(card.getName() + "[ ");
        for (Series series : card.getSeries()) {
            System.out.print(series + ", ");
        }

        System.out.print("\b\b ]");
    }
}
