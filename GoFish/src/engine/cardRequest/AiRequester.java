package engine.cardRequest;

import engine.Card;
import engine.Player;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author adamnark
 */
public class AiRequester implements ICardRequester {

    private LinkedList<Player> otherPlayers;
    private LinkedList<String> availableSerieses;
    private Player me;

    @Override
    public CardRequest requestCard(Player me, Collection<Player> otherPlayers, Collection<String> availableSerieses) {
        this.otherPlayers = new LinkedList<>(otherPlayers);
        this.availableSerieses = new LinkedList<>(availableSerieses);
        this.me = me;
        
        Player otherPlayer = pickRandomPlayer();
        Card randomCard = makeRandomCard();
        
        return new CardRequest(otherPlayer, randomCard);
    }

    private Card makeRandomCard() {
        Card randomCard = new Card();
        randomCard.setName("randomized card name!");
        randomCard.getSerieses().add(this.me.getHand().getFirst().getSerieses().getFirst());
        int seriesCount = this.me.getHand().getFirst().getSerieses().size();

        Random rand = new Random();
        while (randomCard.getSerieses().size() < seriesCount) {
            String randomSeries = this.availableSerieses.get(rand.nextInt(availableSerieses.size()));
            if (!randomCard.isInSeries(randomSeries)) {
                randomCard.addSeries(randomSeries);
            }
        }

        return randomCard;
    }

    private Player pickRandomPlayer() {
        Random rand = new Random();
        int nextPlayer = rand.nextInt(otherPlayers.size());

        return otherPlayers.get(nextPlayer);
    }
}
