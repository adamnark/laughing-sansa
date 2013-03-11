package examples.helloworld;

/**
 *
 * @author blecherl
 */
public class StringsExample {

    public static void main(String[] args) {
        boo();
    }

    public static void boo() {
        String myName = "Jack"; // new String("Jack");
        foo(myName);
        //myName still referencing "Jack"

        myName = addMrPrefix(myName);
        //myName is now referencing to a new String
        //containing the value "Mr. Jack"
        //The original String - "Jack" - can be released by the
        //Garbage Collector
    }

    public static void foo(String s) {
        s = "123"; // new String("123);
        //the original string that was sent to this method will remain
        //unchanged; s now reference a new String - "123" - that is accessible
        //inside the method foo only
    }

    //to change a string, you need to create a new string
    //and assing it back to the original variable you used
    public static String addMrPrefix(String name) {
        String newName = "Mr. " + name.toUpperCase();
        return newName;
    }
}
