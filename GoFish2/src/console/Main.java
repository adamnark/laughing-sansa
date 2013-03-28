package console;

import engine.Engine;
import engine.Validator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class Main {

    private static String defaultXmlPath = "xml-resources/gofish.xml";
    private static String userXmlPath;
    private static SettingsFromConsole settingsFromConsole;
    private static SettingsFromXML settingsFromXML;
    private static boolean isLastGameFromXML;

    public static void main(String[] args) {

        String xmlPathString = getArgs(args);
        if (xmlPathString == null) {
            return;
        }

        Engine engine = createEngine();
        if (engine == null) {
            return;
        }

        if (new Validator(engine).validateEngineState() == false) {
            System.out.println("Bad settings provided, validation failed.");
            return;
        }

        boolean playAgain = false;
        do {
            ConsoleGame game = new ConsoleGame(engine);
            game.PlayGame();
            if (askUserIfRematch()) {
                try {
                    engine = resetEngine();
                } catch (Exception ex) {
                    System.out.println("an error occured when trying to reset engine from user settings.");
                    ex.printStackTrace();
                }
            } else {
                playAgain = false;
            }
        } while (true);
    }

    private static String getArgs(String[] args) {
        if (args.length > 1) {
            System.out.println("Valid argument is a path to an XML.");
            userXmlPath = null;
        } else if (args.length == 1) {
            userXmlPath = args[0];
        } else if (args.length == 0) {
            userXmlPath = defaultXmlPath;
        } else {
            userXmlPath = null;
        }
        return userXmlPath;
    }

    private static boolean askFromXML() {
        System.out.println("Do you want to use xml or enter settings manually?\n1-xml, 2-manual\nchoice:");
        int choice = console.utils.InputUtils.readInteger(1, 2);
        return choice == 1;
    }

    private static Engine createEngine() {
        Main.isLastGameFromXML = askFromXML();
        if (Main.isLastGameFromXML) {
            try {
                settingsFromXML = new SettingsFromXML(Main.userXmlPath);
            } catch (JAXBException ex) {
                System.out.println(Main.userXmlPath + " failed to load.");
                return null;
            }
        }

        return Main.isLastGameFromXML
                ? settingsFromXML.makeEngineFromXML()
                : settingsFromConsole.makeEngineFromConsole();
    }

    private static boolean askUserIfRematch() {
        System.out.println("Would you like to play again with the same settings?");
        return console.utils.InputUtils.readBoolean();
    }

    private static Engine resetEngine() throws Exception {
        return isLastGameFromXML
                ? Main.settingsFromXML.makeEngineFromXML()
                : Main.settingsFromConsole.createEngineWithLastSettings();
    }

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
