package console;

import engine.Engine;
import engine.GameSettings;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author adam
 */
public class SettingsFromConsole {

    private int numberOfPlayers;
    private boolean allowMutipleRequestsFromUser;
    private boolean forceShowOfSeriesFromUser;
    private LinkedList<Player> players;

    public SettingsFromConsole() {
        this.players = new LinkedList<>();
    }

    public Engine makeEngineFromConsole() {
        this.numberOfPlayers = getNumberOfPlayers();
        Engine engine = new Engine();
        engine.setGameSettings(getGameSettingsFromUser());

        addPlayersFromUser(engine, numberOfPlayers);

        return engine;
    }

    public Engine createEngineWithLastSettings() throws Exception {
        if (this.players.isEmpty()) {
            throw new Exception("Can't restore settings, first time calling!");
        }

        Engine engine = new Engine();
        engine.setGameSettings(getLastGameSettings());
        engine.addPlayers(addPlayersFromHistory());
        return engine;
    }

    private boolean getAllowMutipleRequestsFromUser() {
        System.out.println("Allow Mutiple Requests? ");
        this.allowMutipleRequestsFromUser = console.utils.InputUtils.readBoolean();
        return this.allowMutipleRequestsFromUser;
    }

    private boolean getForceShowOfSeriesFromUser() {
        System.out.println("Force Show Of Series? ");
        this.forceShowOfSeriesFromUser = console.utils.InputUtils.readBoolean();
        return this.forceShowOfSeriesFromUser;
    }

    private int getNumberOfPlayers() {
        System.out.println("How many players? minimum 3, maximum 6:");
        this.numberOfPlayers = console.utils.InputUtils.readInteger(3, 6);
        return this.numberOfPlayers;
    }

    private GameSettings getGameSettingsFromUser() {
        GameSettings settings = new GameSettings();
        settings.setAllowMutipleRequests(getAllowMutipleRequestsFromUser());
        settings.setForceShowOfSeries(getForceShowOfSeriesFromUser());

        return settings;
    }

    private void addPlayersFromUser(Engine engine, int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Enter details for player number " + i + ": ");
            Player player = getPlayerFromUser(i);
            generateHandForPlayer(player, i);
            engine.addPlayer(player);
            this.players.add(new Player(player));
        }
    }

    private Player getPlayerFromUser(int playerNumber) {
        Player player;
        System.out.println("Is player # " + playerNumber + " human?");
        boolean isHuman = console.utils.InputUtils.readBoolean();

        if (!isHuman) {
            player = Player.createAIPlayer();
            player.setName(makeComputerName(playerNumber));

        } else {
            player = new Player(
                    new console.humanPlayerInterface.HumanFourPicker(),
                    new console.humanPlayerInterface.HumanRequestMaker());
            player.setName(readHumanName(playerNumber));
        }

        return player;
    }

    private String makeComputerName(int playerNumber) {
        return "BOT #" + playerNumber;
    }

    private String readHumanName(int playerNumber) {
        System.out.println("Name for player #" + playerNumber + ": ");
        return console.utils.InputUtils.readLineNotEmpty();
    }

    private void generateHandForPlayer(Player player, int playerNumber) {
        RandomStringGenerator rsg = new RandomStringGenerator();
        generateSeriesForPlayer(player, rsg, "floop");
        generateSeriesForPlayer(player, rsg, "scoop");
        generateSeriesForPlayer(player, rsg, "droop");
        generateSeriesForPlayer(player, rsg, "kloop");
    }

    private void generateSeriesForPlayer(Player player, RandomStringGenerator rsg, String seriesName) {
        for (int i = 0; i < 4; i++) {
            Card generatedCard = new Card();
            generatedCard.addSeries(new Series(seriesName));
            generatedCard.setName(seriesName + " " + rsg.makeRandomString());
            player.getHand().addCardToHand(generatedCard);
        }
    }

    private GameSettings getLastGameSettings() {
        GameSettings gameSettings = new GameSettings();
        gameSettings.setAllowMutipleRequests(this.allowMutipleRequestsFromUser);
        gameSettings.setForceShowOfSeries(this.forceShowOfSeriesFromUser);
        return gameSettings;

    }

    private Collection<Player> addPlayersFromHistory() {
        LinkedList<Player> lst = new LinkedList<>();

        for (Player player : this.players) {
            lst.add(new Player(player));
        }

        return lst;
    }

    private class RandomStringGenerator {

        private SecureRandom random = new SecureRandom();

        public String makeRandomString() {
            return new BigInteger(32, random).toString(32);
        }
    }
}
