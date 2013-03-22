package console;

import engine.Engine;
import generated.Gofish;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import settings.GameSettings;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class GoFishConsole {

    public static void main(String[] args) {
        String xmlPathString = "src\\xml\\resources\\gofish.xml";
        Engine engine = null;
        
        try {
            engine = makeEngineFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println("Bad XML file provided.");
            return;
        }

        System.out.println("wait here.");

    }

    private static engine.Engine makeEngineFromXML(String xmlPathString) throws JAXBException {
        SettingsFromXML settingsFromXML = new SettingsFromXML(xmlPathString);
        engine.Engine engine = new Engine();
        engine.setGameSettings(settingsFromXML.generateGameSettings());
        engine.setPlayers(settingsFromXML.generatePlayers());

        return engine;
    }
}
