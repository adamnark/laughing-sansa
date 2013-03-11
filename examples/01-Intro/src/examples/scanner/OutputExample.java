package examples.scanner;

/**
 * This is a multiple lines comment
 * @author blecherl
 */

//to run this program press Shift+F6
//to run this program in debug mode press Ctrl+F5
public class OutputExample {

    //in order to run a java program, there must be one entry point --> a method that the JVM calls first
    //in java this method will ALWAYS look like this:
    //public static void main(String[] args)
    public static void main(String[] args) {
        //Tip! type 'psvm' (wihtout quotation marks) and then press the TAB key to automatically create the "main" method

        //prints a string to the program console and moves to the next line
        System.out.println("Hello World!");
        //Tip! type sout (wihtout quotation marks) and then press the TAB key to automatically create the System.out.println command

        //System is a class (just like any other class)
        //out is a static public variable defined inside System
        //this is why we can call it directly - System.out
        //out is an instance of class PrintStream

        //println is a method defined on the class PrintStream
        //it has several overloads, one of which accepts String as a parameter
    }
}