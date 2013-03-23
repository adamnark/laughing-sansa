package console;

import engine.Engine;
import engine.players.BadCardRequestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class Main {

    public static void main(String[] args) {
        myprint("game starting");
        String xmlPathString = "";
        try {
            xmlPathString = getArgs(args);
        } catch (BadArgumentException ex) {
            System.out.println("Valid argument is a path to an XML.");
        }

        Engine engine = new Engine();

        try {
            engine = makeEngineFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println("Bad XML file provided.");
        }

        int count = 0;
        do {
            myprint("playing turn : " + engine.getCurrentPlayer().toString());
            try {
                engine.Turn();
            } catch (BadCardRequestException ex) {
                myprint("bad card request!!");
                ex.printStackTrace();
            }

            printEventQueue(engine);
            myprint("\n");

            count++;
        } while (!engine.isGameOver());
        System.out.println("count=" + count);
    }

    private static void printEventQueue(Engine engine) {
        while (!engine.getEventQueue().isEmpty()) {
            myprint(engine.getEventQueue().pop().toString());
        }
    }

    private static engine.Engine makeEngineFromXML(String xmlPathString)
            throws JAXBException {
        SettingsFromXML settingsFromXML = new SettingsFromXML(xmlPathString);
        engine.Engine engine = new Engine();
        engine.setGameSettings(settingsFromXML.generateGameSettings());
        engine.addPlayers(settingsFromXML.generatePlayers());

        return engine;
    }

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

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
