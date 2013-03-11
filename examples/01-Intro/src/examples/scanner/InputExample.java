package examples.scanner;

//the class Scanner is in the library java.util
import java.util.InputMismatchException;
import java.util.Scanner;

//in order to use it we need to import it first using the import statement
/**
 *
 * @author blecherl
 */
public class InputExample {

    public static void main(String[] args) {

	//in is a static public variable defined inside System

	Scanner scanner = new Scanner(System.in);

	//the Scanner class has many methods to read different values
	//this is why we can call it directly - System.in
	//in is an instance of class InputStream

	boolean validInput = true;
	do {
	    validInput = true;
	    System.out.print("Enter an integer: ");
	    //read an integer from the console
	    try {
		int i = scanner.nextInt();
	    } catch (InputMismatchException exception) {
		System.out.println("This is not a number!!!");
		validInput = false;
	    }
	} while (validInput == false);

	System.out.print("Enter an double: ");
	//read a double from the console
	double d = scanner.nextDouble();

	System.out.print("Enter a string: ");
	//read a string from the console
	String str = scanner.next();

	//these methods are a blocking methods
	//this means that the code won't run until the scanner gets a value
	//in this case, until the user presses the Enter key
	//the separator of the values is space

//	System.out.println("int = " + i + ", double = " + d + ", string = " + str);
    }
}
