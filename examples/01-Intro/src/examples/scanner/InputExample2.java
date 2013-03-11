package examples.scanner;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class InputExample2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name: ");
        //read a string until the first space character
        String firstName = scanner.next();

        System.out.println("Enter last name: ");
        String lastName = scanner.next();

        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);

        //There is going to be a problem if you run this program and enter:
        //'John Locke' and then press Enter
        //(instead of entering 'John' + Enter and then 'Locke' + Enter
        //Since you've entere a space in the first line, the second word ('Locke') was
        //entered in a buffer
        //the next time scanner.next() was called, the buffer immediatly returned
        //the second word - without waiting for the user
    }
}
