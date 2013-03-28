package console;

import console.utils.GameStatusPrinter;
import engine.Engine;
import engine.Validator;
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
        String xmlPathString;
        try {
            xmlPathString = getArgs(args);
        } catch (BadArgumentException ex) {
            System.out.println("Valid argument is a path to an XML.");
            return;
        }

        Engine engine = new Engine();
        boolean isMakeEngineFromXML = askFromXML();
        if (isMakeEngineFromXML) {
            engine = makeEngineFromXML(xmlPathString);
            if (engine == null) {
                System.out.println("Bad XML file provided.");
                return;
            }
        } else {
            engine = makeEngineFromConsole();
            if (engine == null){
                System.out.println("Bad settings provided by the user.");
            }
        }

        if (new Validator(engine)
                .validateEngineState() == false) {
            System.out.println("Bad settings provided.");
            return;
        }

        System.out.println(
                "game starting");
        int count = 0;


        do {
            GameStatusPrinter.printGameStatus(engine);
            engine.Turn();
            //printEventQueue(engine);
            count++;
            System.out.println(engine.getCurrentPlayer().getName() + "'s turn is over.");
        } while (!engine.isGameOver());

        System.out.println(
                "GAME OVER");
        GameStatusPrinter.printGameStatus(engine);

        System.out.println(
                "count=" + count);
    }

    private static engine.Engine makeEngineFromXML(String xmlPathString) {// throws JAXBException {
        engine.Engine engine;
        try {
            SettingsFromXML settingsFromXML = new SettingsFromXML(xmlPathString);
            engine = new Engine();
            engine.setGameSettings(settingsFromXML.generateGameSettings());
            engine.addPlayers(settingsFromXML.generatePlayers());
        } catch (JAXBException ex) {
            return null;
        }
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

    private static boolean askFromXML() {
        System.out.println("Do you want to use xml or enter settings manually?\n1-xml, 2-manual\nchoice:");
        int choice = console.utils.InputUtils.readInteger(1, 2);

        return choice == 1;


    }

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
