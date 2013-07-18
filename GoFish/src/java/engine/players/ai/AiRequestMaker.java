package engine.players.ai;

import engine.cards.Card;
import engine.cards.Series;
import engine.players.Hand;
import engine.players.Player;
import engine.request.IRequestMaker;
import engine.request.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author adamnark
 */
public class AiRequestMaker implements IRequestMaker {

    private Hand hand;
    private List<Series> availableSeries;
    private List<Player> otherPlayers;

    @Override
    public Request makeRequest(Hand hand, List<Series> availableSeries, List<Player> otherPlayers) {
        if (hand.getCards().isEmpty()) {
            return null;
        }

        this.hand = hand;
        this.availableSeries = availableSeries;
        this.otherPlayers = otherPlayers;

        Card randomCard = makeRandomCard();
        Player randomPlayer = pickRandomPlayer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return new Request(randomPlayer, randomCard);
    }

    private Card makeRandomCard() {
        Card randomCard = new Card();
        Random rand = new Random();

        randomCard.setName("***");
        ArrayList<Card> cards = this.hand.getCards();


        Card randomCardFromHand = cards.get(rand.nextInt(cards.size()));
        Series randomSeriesFromHand = randomCardFromHand.getSeries().get(rand.nextInt(randomCardFromHand.getSeries().size()));
        randomCard.getSeries().add(randomSeriesFromHand);

        int seriesCount = this.hand.getCards().get(0).getSeries().size();
        while (randomCard.getSeries().size() < seriesCount) {
            Series randomSeries = this.availableSeries.get(rand.nextInt(this.availableSeries.size()));
            if (!randomCard.isInSeries(randomSeries)) {
                randomCard.addSeries(randomSeries);
            }
        }

        return randomCard;
    }

    private Player pickRandomPlayer() {
        Random rand = new Random();
        int randomIndex;
        do {
            randomIndex = rand.nextInt(otherPlayers.size());
        } while (!otherPlayers.get(randomIndex).isPlaying());

        return otherPlayers.get(randomIndex);
    }
}
