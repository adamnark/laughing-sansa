package console;

import engine.Engine;
import engine.InvalidMoveException;
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

    private static String getArgs(String[] args)
            throws BadArgumentException {
        String xmlPathString = "xml-resources\\gofish.xml";
        if (args.length > 1) {
            throw new BadArgumentException();
        } else if (args.length == 1) {
            xmlPathString = args[0];
        }

        return xmlPathString;
    }

    private static void myprint(String s) {
        System.out.println("\t" + s);
    }

    public static void main(String[] args) {
        myprint("game starting");
        String xmlPathString;
        try {
            xmlPathString = getArgs(args);
        } catch (BadArgumentException ex) {
            Logger.getLogger(GoFishConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("You can specify an XML game file, but that's it!");
            return;
        }


        Engine engine = new Engine();

        try {
            engine = makeEngineFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println("Bad XML file provided.");
            return;
        }
        myprint("Unmarshalled settings from " + xmlPathString);

        while (!engine.checkEndgame()) {
            try {
                myprint("playing turn : " + engine.getCurrentPlayer().toString());
                engine.playTurn();
            } catch (InvalidMoveException ex) {
                Logger.getLogger(GoFishConsole.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (!engine.getEventQueue().isEmpty()) {
                myprint(engine.getEventQueue().pop().toString());
            }
        }
    }

    private static engine.Engine makeEngineFromXML(String xmlPathString) throws JAXBException {
        SettingsFromXML settingsFromXML = new SettingsFromXML(xmlPathString);
        engine.Engine engine = new Engine();
        engine.setGameSettings(settingsFromXML.generateGameSettings());
        engine.setPlayers(settingsFromXML.generatePlayers());

        return engine;
    }

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
