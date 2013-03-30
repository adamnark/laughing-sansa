package console;

import engine.Engine;
import engine.Validator;
import console.utils.InputUtils;
import javax.xml.bind.JAXBException;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class Main {

    private static final String defaultXmlPath = "xml-resources/gofish.xml";
    private static String userXmlPath;
    private static SettingsFromConsole settingsFromConsole;
    private static SettingsFromXML settingsFromXML;
    private static boolean isLastGameFromXML;

    public static void main(String[] args) {
        String xmlPathString = getArgs(args);
        if (xmlPathString == null) {
            return;
        }

        Engine engine;
        boolean isValidSettingsProvided;
        do {
            engine = createEngine();
            if (engine == null) {
                return;
            }

            if (new Validator(engine).validateEngineState() == false) {
                System.out.println("Bad settings provided, validation failed.");
                isValidSettingsProvided = false;
            } else {
                isValidSettingsProvided = true;
            }
        } while (!isValidSettingsProvided);

        printWelcome();
        boolean playAgain;
        do {
            ConsoleGame game = new ConsoleGame(engine);
            try {
                game.PlayGame();
            } catch (Exception ex) {
                System.out.println("an error occured during play, game controller out of state.");
                ex.printStackTrace();
                return;
            }
            if (askUserIfRematch()) {
                try {
                    engine = resetEngine();
                } catch (Exception ex) {
                    System.out.println("an error occured when trying to reset engine.");
                    ex.printStackTrace();
                }
                playAgain = true;
            } else {
                playAgain = false;
            }
        } while (playAgain);

        printGoodbye();
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
        System.out.println("Do you want to use xml or enter settings manually?");
        System.out.println("(xml settings file I have is " + Main.userXmlPath + ")");
        System.out.println("1-xml, 2-manual\nchoice:");
        int choice = InputUtils.readInteger(1, 2);
        return choice == 1;
    }

    private static Engine createEngine() {
        Main.isLastGameFromXML = askFromXML();
        if (Main.isLastGameFromXML) {
            try {
                settingsFromXML = new SettingsFromXML(Main.userXmlPath);
            } catch (JAXBException ex) {
                System.out.println(Main.userXmlPath + " failed to load.\nDefine a game manually then:\n");
                Main.isLastGameFromXML = false;

            }
        }
        if (!Main.isLastGameFromXML) {
            Main.settingsFromConsole = new SettingsFromConsole();
        }

        return Main.isLastGameFromXML
                ? settingsFromXML.makeEngineFromXML()
                : settingsFromConsole.makeEngineFromConsole();
    }

    private static boolean askUserIfRematch() {
        System.out.println("Would you like to play again with the same settings?");
        return InputUtils.readBoolean();
    }

    private static Engine resetEngine() throws Exception {
        return isLastGameFromXML
                ? Main.settingsFromXML.makeEngineFromXML()
                : Main.settingsFromConsole.createEngineWithLastSettings();
    }

    private static void printWelcome() {
        final String welcome = "                                     _          _      \n"
                + "                                    | | o      | |    |\n"
                + "                         __,  __    | |     ,  | |    |\n"
                + "                        /  | /  \\_  |/  |  / \\_|/ \\   |\n"
                + "                        \\_/|/\\__/   |__/|_/ \\/ |   |_/o\n"
                + "                          /|        |\\                 \n"
                + "                          \\|        |/ ";

        System.out.println(welcome);
    }

    private static void printGoodbye() {
        final String goodbye = "                                           _              \n"
                + "                                        | | |            |\n"
                + "                       __,  __   __   __| | |         _  |\n"
                + "                      /  | /  \\_/  \\_/  | |/ \\_|   | |/  |\n"
                + "                      \\_/|/\\__/ \\__/ \\_/|_/\\_/  \\_/|/|__/o\n"
                + "                        /|                        /|      \n"
                + "                        \\|                        \\| ";

        System.out.println(goodbye);
    }

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
