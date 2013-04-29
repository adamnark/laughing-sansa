package swing.components.settings.manual;

import engine.Engine;
import engine.GameSettings;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedList;
import swing.playerinterface.SwingFourPicker;
import swing.playerinterface.SwingRequestMaker;
import swing.utils.playeritem.PlayerItem;
import swing.utils.playeritem.PlayerType;

/**
 *
 * @author adamnark
 */
public class GUIEngineMaker {

    private LinkedList<PlayerItem> playerItems;
    private boolean allowMutipleRequests;
    private boolean forceShowOfSeries;

    public GUIEngineMaker() {
        this.playerItems = new LinkedList<>();
    }

    public Engine makeEngine() {
        Engine engine = new Engine();
        engine.setGameSettings(initGameSettings());
        addPlayers(engine);

        return engine;
    }

    public void addPlayer(PlayerItem player) {
        this.playerItems.add(player);
    }

    public void setAllowMutipleRequests(boolean allowMutipleRequests) {
        this.allowMutipleRequests = allowMutipleRequests;
    }

    public void setForceShowOfSeries(boolean forceShowOfSeries) {
        this.forceShowOfSeries = forceShowOfSeries;
    }

    private GameSettings initGameSettings() {
        GameSettings gs = new GameSettings();

        gs.setAllowMutipleRequests(allowMutipleRequests);
        gs.setForceShowOfSeries(forceShowOfSeries);

        return gs;
    }

    private void addPlayers(Engine engine) {
        for (PlayerItem playerItem : playerItems) {
            Player player = makePlayer(playerItem);
            generateHandForPlayer(player);
            engine.addPlayer(player);
        }
    }

    private Player makePlayer(PlayerItem playerItem) {
        Player player;
        if (playerItem.getPlayerType() == PlayerType.COMPUTER) {
            player = Player.createAIPlayer();
        } else {
            player = new Player(new SwingFourPicker(), new SwingRequestMaker());
        }
        player.setName(playerItem.getName());

        return player;
    }

    private void generateHandForPlayer(Player player) {
        RandomStringGenerator rsg = new RandomStringGenerator();
        generateSeriesForPlayer(player, rsg, "Ao", "Bo","Co");
        generateSeriesForPlayer(player, rsg, "Ki","Mi","Li");
        generateSeriesForPlayer(player, rsg, "Za","Xa","Va");
    }

    private void generateSeriesForPlayer(Player player, RandomStringGenerator rsg, String seriesName, String seriesName2, String seriesName3) {
        for (int i = 0; i < 4; i++) {
            Card generatedCard = new Card();
            generatedCard.addSeries(new Series(seriesName));
            generatedCard.addSeries(new Series(seriesName2));
            generatedCard.addSeries(new Series(seriesName3));
            generatedCard.setName(seriesName + " " + rsg.makeRandomString());
            player.getHand().addCardToHand(generatedCard);
        }
    }

    private class RandomStringGenerator {

        private SecureRandom random = new SecureRandom();

        public String makeRandomString() {
            return new BigInteger(16, random).toString(16);
        }
    }
}
