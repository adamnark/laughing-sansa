package engine.Moves;

import engine.Card;
import engine.Player;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author adamnark
 */
public class AiMover implements IMover{
    private int playerNumber;

    @Override
    public void setPlayerNumber(int playerIndex) {
        this.playerNumber = playerIndex;
    }

    @Override
    public Move makeMove(LinkedList<Player> players, LinkedList<LinkedList<String>> availableCards) {
        if (players == null || availableCards == null){
            throw new NullPointerException("makeMove");
        }
        
        Player otherPlayer = pickRandomPlayer(players);
        Card randomCard = makeRandomCard(availableCards);
        
        return new Move(otherPlayer, randomCard);
    }

    private Card makeRandomCard(LinkedList<LinkedList<String>> availableCards) {
        LinkedList<String> randomFaces = new LinkedList<>();
        
        for (LinkedList<String> series: availableCards) {
            String randomFace = pickRandomFace(series);
            randomFaces.add(randomFace);
        }
        
        return new Card(randomFaces);
    }

    private Player pickRandomPlayer(LinkedList<Player> players) {
        int nextPlayer = (this.playerNumber + 1) % players.size();
        return players.get(nextPlayer);
    }

    private String pickRandomFace(LinkedList<String> series) {
        Random rand = new Random();
        int randomNum = rand.nextInt(series.size());

        return series.get(randomNum);
    }
}
