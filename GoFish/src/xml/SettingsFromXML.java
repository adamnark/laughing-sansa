package xml;

import engine.Card;
import generated.Gofish;
import generated.PlayerType;
import java.io.File;
import java.util.HashSet;
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
    private GameSettings gameSettings;
    
    public SettingsFromXML(String xmlFileName) 
            throws JAXBException {
        try {
            File file = new File(xmlFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Gofish.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.gofish = (Gofish) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
//            ex.printStackTrace();
            throw ex;
        }

    }

    public GameSettings generateGameSettings() {
        this.gameSettings = new GameSettings();
        this.gameSettings.setAllowMutipleRequests(this.gofish.getSettings().isAllowMutipleRequests());
        this.gameSettings.setForceShowOfSeries(this.gofish.getSettings().isForceShowOfSeries());
        this.gameSettings.addSeriesesToAvailable(this.getAvailableSerieses());
        
        return gameSettings;
    }

    public LinkedList<engine.Player> generatePlayers() {
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

    private HashSet<String> getAvailableSerieses() {
        HashSet<String> serieses = new HashSet<>();
        
        for (generated.Player generatedPlayer : this.gofish.getPlayers().getPlayer()) {
            for (generated.Card generatedCard : generatedPlayer.getCards().getCard()) {
                for (String generatedSeries : generatedCard.getSeries()) {
                    serieses.add(generatedSeries);
                }
            }
        }
        
        return serieses;
    }
}
