package console;

import engine.Engine;
import engine.Validator;
import javax.xml.bind.JAXBException;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
public class Main {

    private static String defaultXmlPath = "xml-resources/gofish.xml";

    public static void main(String[] args) {

        String xmlPathString = getArgs(args);
        if (xmlPathString == null) {
            return;
        }

        SettingsFromConsole settingsFromConsole = new SettingsFromConsole();
        SettingsFromXML settingsFromXML;
        try {
            settingsFromXML = new SettingsFromXML(xmlPathString);
        } catch (JAXBException ex) {
            System.out.println(xmlPathString + " failed to load.");
            settingsFromXML = null;
        }
        Engine engine = createEngine(settingsFromConsole, settingsFromXML);
        if (engine == null) {
            return;
        }

        if (new Validator(engine).validateEngineState() == false) {
            System.out.println("Bad settings provided, validation failed.");
            return;
        }

        boolean playAgain = false;
        do {
            break;
        } while (true);
    }

    private static String getArgs(String[] args) {
        String xmlPathString = Main.defaultXmlPath;
        if (args.length > 1) {
            System.out.println("Valid argument is a path to an XML.");
            return null;
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

    private static Engine createEngine(SettingsFromConsole xmlPathString, SettingsFromXML settingsFromXML) {
        Engine engine;
        boolean isMakeEngineFromXML = askFromXML();
        if (isMakeEngineFromXML) {
            //engine = makeEngineFromXML(xmlPathString);

            if (engine == null) {
                System.out.println("Bad XML file provided, xml unmarshalling failed.");
                return null;
            }
        } else {
            engine = new SettingsFromConsole().makeEngineFromConsole();
            if (engine == null) {
                System.out.println("Bad settings provided by the user.");
                return null;
            }
        }
        return engine;
    }

    private static class BadArgumentException extends Exception {

        public BadArgumentException() {
        }
    }
}
