package console;

import console.utils.GameStatusPrinter;
import engine.Engine;
import engine.players.BadCardRequestException;
import javax.xml.bind.JAXBException;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class Main {

    private static String defaultXmlPath = "xml-resources/gofish.xml";

    public static void main(String[] args) {
        myprint("game starting!");
        String xmlPathString = "";
        try {
            xmlPathString = getArgs(args);
        } catch (BadArgumentException ex) {
            System.out.println("Valid argument is a path to an XML.");
            return;
        }

        Engine engine = new Engine();

        try {
            engine = makeEngineFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println("Bad XML file provided.");
            return;
        }

        int count = 0;
        do {

            GameStatusPrinter.printGameStatus(engine);
            try {
                engine.Turn();
            } catch (BadCardRequestException ex) {
                myprint("bad card request!!");
                ex.printStackTrace();
            }
            //printEventQueue(engine);
            count++;
        } while (!engine.isGameOver());

        GameStatusPrinter.printGameStatus(engine);
        System.out.println("count=" + count);
        System.out.println("ENDGAME");
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
        String xmlPathString = Main.defaultXmlPath;
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
