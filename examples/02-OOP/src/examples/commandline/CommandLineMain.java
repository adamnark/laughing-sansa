package examples.commandline;

/**
 *
 * @author blecherl
 */
public class CommandLineMain {

    /*
     * To add command line arguments from NetBeans
     * Go to the menu: Run --> Set Project Configuration --> Customize...
     * Click New... to create a new configuration
     * Add any arguments in the arguments field
     * You can also choose the Class with the main method in it
     *
     * To run click F6
     */
    public static void main(String[] args) {
        if (args != null) {
            for (int index = 0 ; index < args.length ; index++) {
                String argument = args[index];
                System.out.println("Argument #" + (index+1) + ": " + argument);
            }
        }
    }
}
