package examples.scanner;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class InputExample4Good {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your title: ");
        //read an entire line until the user presses the Enter key
        String title = scanner.next();

        //clear the scanner buffer from the Enter
        scanner.nextLine();

        System.out.println("Enter full name: ");
        //read an entire line until the user presses the Enter key
        String fullName = scanner.nextLine();

        System.out.println("Full name is: " + title + " " + fullName);
    }
}
