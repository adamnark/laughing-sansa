package examples.scanner;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class InputExample3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter full name: ");
        //read an entire line until the user presses the Enter key
        String fullName = scanner.nextLine();

        System.out.println("Full name is: " + fullName);
    }
}
