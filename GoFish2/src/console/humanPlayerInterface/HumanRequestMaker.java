package console.humanPlayerInterface;

import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import java.util.List;
import engine.request.IRequestMaker;
import engine.request.Request;

/**
 *
 * @author adamnark
 */
public class HumanRequestMaker implements IRequestMaker {

    @Override
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        Request request;
        boolean ok = false;
        do {
            Player otherPlayer = pickPlayer(otherPlayers);
            Card requestCard = pickCard(availableSeries, hand.getCards().get(0).getSeries().size());
            request = new Request(otherPlayer, requestCard);
            ok = engine.request.RequestValidator.validateRequest(request, hand);
            if (!ok) {
                System.out.println("couldn't validate this request, make another.");
            }
        } while (!ok);

        return request;
    }

    private Player pickPlayer(List<Player> otherPlayers) {
        console.utils.GameStatusPrinter.printPlayers(otherPlayers);
        System.out.println("Pick a Player :");
        int playerNum = console.utils.InputUtils.readInteger(1, otherPlayers.size());

        return otherPlayers.get(playerNum);
    }

    private Card pickCard(List<Series> availableSeries, int numOfSeriesToPick) {
        Card pickedCard = new Card();
        pickedCard.setName("%%%");

        printSeries(availableSeries);
        for (int i = 1; i <= numOfSeriesToPick; i++) {
            System.out.print("pick series #" + i + ": ");
            int seriesNum = console.utils.InputUtils.readInteger(1, availableSeries.size());
            pickedCard.addSeries(availableSeries.get(seriesNum));
        }

        return pickedCard;
    }

    private void printSeries(List<Series> availableSeries) {
        int i = 1;
        for (Series series : availableSeries) {
            System.out.print(i + ": ");
            System.out.println(series.getName());
            i++;
        }
    }
}
