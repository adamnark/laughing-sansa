package xml;

import engine.Card;
import engine.Player;
import generated.Cards;
import generated.Gofish;
import generated.PlayerType;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import settings.GameSettings;

/**
 *
 * @author adamnark
 */
public class SettingsFromXML {

    private Gofish gofish;

    public SettingsFromXML(String xmlFileName) 
            throws JAXBException {
        try {
            File file = new File(xmlFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Gofish.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.gofish = (Gofish) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public GameSettings generateGameSettings() {
        GameSettings gameSettings = new GameSettings();
        gameSettings.setRepeatTurnWhenSuccessful(this.gofish.getSettings().isAllowMutipleRequests());
        gameSettings.setRevealFlushOnSuccessfulPlay(this.gofish.getSettings().isForceShowOfSeries());
        
        
        
        
        return gameSettings;
    }

    public LinkedList<Player> generatePlayers() {
        LinkedList<engine.Player> lst = new LinkedList<>();
        
        for (generated.Player generatedPlayer : gofish.getPlayers().getPlayer()) {
            boolean isAIPlayer = generatedPlayer.getType().equals(PlayerType.COMPUTER);
            
            engine.Player enginePlayer = new engine.Player(isAIPlayer);
            
            enginePlayer.setName(generatedPlayer.getName());
            
            for (Card card : convertCards(generatedPlayer.getCards().getCard())) {
                enginePlayer.addCardToHand(card);    
            }
            
            lst.add(enginePlayer);
        }
        
        return lst;
        
    }

    private LinkedList<engine.Card> convertCards(List<generated.Card> generatedCards) {
        LinkedList<engine.Card> lst = new LinkedList<>();
        
        for (generated.Card generatedCard : generatedCards) {
            engine.Card engineCard = new Card(generatedCard.getSeries());
            engineCard.setName(generatedCard.getName());
            lst.add(engineCard);
        }
        
        return lst;
    }
}
