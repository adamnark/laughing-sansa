package xml;

import engine.GameSettings;
import engine.cards.Card;
import generated.Gofish;
import generated.PlayerType;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import swing.playerinterface.SwingFourPicker;
import swing.playerinterface.SwingRequestMaker;

/**
 *
 * @author adamnark
 */
public class SettingsFromXML {

    private Gofish gofish;
    private GameSettings gameSettings;

    public SettingsFromXML(String xmlFileName)
            throws JAXBException {
        File file = new File(xmlFileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(Gofish.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.gofish = (Gofish) jaxbUnmarshaller.unmarshal(file);
    }

    public engine.Engine makeEngine() {
        engine.Engine engine;
        engine = new engine.Engine();
        engine.setGameSettings(this.generateGameSettings());
        engine.addPlayers(this.generatePlayers());
        return engine;
    }

    public GameSettings generateGameSettings() {
        this.gameSettings = new GameSettings();
        this.gameSettings.setAllowMutipleRequests(this.gofish.getSettings().isAllowMutipleRequests());
        this.gameSettings.setForceShowOfSeries(this.gofish.getSettings().isForceShowOfSeries());

        return gameSettings;
    }

    private LinkedList<engine.players.Player> generatePlayers() {
        LinkedList<engine.players.Player> lst = new LinkedList<>();

        for (generated.Player generatedPlayer : gofish.getPlayers().getPlayer()) {
            lst.add(convertToEnginePlayer(generatedPlayer));
        }

        return lst;
    }

    private engine.players.Player convertToEnginePlayer(generated.Player generatedPlayer) {
        boolean isAIPlayer = generatedPlayer.getType().equals(PlayerType.COMPUTER);


        engine.players.Player enginePlayer =
                isAIPlayer
                ? engine.players.Player.createAIPlayer()
                : new engine.players.Player(new SwingFourPicker(), new SwingRequestMaker());
        enginePlayer.setName(generatedPlayer.getName());


        for (Card card : convertCards(generatedPlayer.getCards().getCard())) {
            enginePlayer.getHand().addCardToHand(card);
        }

        return enginePlayer;
    }

    private LinkedList<engine.cards.Card> convertCards(List<generated.Card> generatedCards) {
        LinkedList<engine.cards.Card> lst = new LinkedList<>();

        for (generated.Card generatedCard : generatedCards) {
            engine.cards.Card engineCard = new Card(generatedCard.getSeries());
            engineCard.setName(generatedCard.getName());
            lst.add(engineCard);
        }

        return lst;
    }
}
