package examples.scanner;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class InputExample4Bad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your title: ");
        //read an entire line until the user presses the Enter key
        String title = scanner.next();

        System.out.println("Enter full name: ");
        //read an entire line until the user presses the Enter key
        String fullName = scanner.nextLine();

        System.out.println("Full name is: " + title + " " + fullName);

        //There is a problem after calling scanner.next():
        //the Enter key the user presses was added to the buffer
        //so when scanner.nextLine() was called it got an emtpy string

        //the solution is to call scanner.nextLine() after calling scanner.next()
        //in order to clear the buffer from the single Enter key
    }
}
