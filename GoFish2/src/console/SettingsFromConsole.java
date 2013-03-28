package console;

import engine.Engine;
import engine.GameSettings;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;

/**
 *
 * @author adam
 */
public class SettingsFromConsole {

    public Engine makeEngineFromConsole() {
        engine.Engine engine = new engine.Engine();
        engine.setGameSettings(getGameSettingsFromUser());

        int numberOfPlayers = getNumberOfPlayers();
        addPlayersFromUser(engine, numberOfPlayers);

        return engine;

    }

    private boolean getAllowMutipleRequestsFromUser() {
        System.out.println("Allow Mutiple Requests? ");

        return console.utils.InputUtils.readBoolean();
    }

    private boolean getForceShowOfSeriesFromUser() {
        System.out.println("Force Show Of Series? ");

        return console.utils.InputUtils.readBoolean();
    }

    private int getNumberOfPlayers() {
        System.out.println("How many players? minimum 3, maximum 6:");

        return console.utils.InputUtils.readInteger(3, 6);
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
        generateSeriesForPlayer(player, playerNumber, "bloop");
        generateSeriesForPlayer(player, playerNumber, "scoop");
        generateSeriesForPlayer(player, playerNumber, "droop");
        generateSeriesForPlayer(player, playerNumber, "kloop");
    }

    private void generateSeriesForPlayer(Player player, int playerNumber, String seriesName) {
        for (int i = 0; i < 4; i++) {
            Card generatedCard = new Card();
            generatedCard.addSeries(new Series(seriesName));
            generatedCard.setName(seriesName + " " + playerNumber);
            player.getHand().addCardToHand(generatedCard);
        }
    }
}
