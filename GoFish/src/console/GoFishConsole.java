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
        SettingsFromXML settingsFromXML = null;
        try {
            settingsFromXML = new SettingsFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println("couldn't read game settings from " + xmlPathString);
        }


        System.out.println("wait here.");

    }
}
