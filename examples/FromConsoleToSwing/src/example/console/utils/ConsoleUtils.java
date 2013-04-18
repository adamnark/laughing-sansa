package example.console.utils;

import java.util.Scanner;

public class ConsoleUtils {

    private static Scanner scanner;

    static {
	scanner = new Scanner(System.in);
    }

    public static String getStringFromUser() {
	return scanner.nextLine();
    }

    public static int getNumberFromUser(int min, int max) {
	int result = 0;
	boolean isValidResult = false;
	while (isValidResult == false) {
	    try {
		result = scanner.nextInt();
		if (result < min) {
		    throw new RuntimeException("Value is less than " + min);
		} else if (result > max) {
		    throw new RuntimeException("Value is greater than " + max);
		}
		isValidResult = true;
	    } catch (RuntimeException exception) {
		isValidResult = false;
		System.out.println("You muse enter a number between " + min + " and " + max);
	    }
	}
	scanner.nextLine();
	return result;
    }

    public static int printMenu(String... options) {
	if (options == null) {
	    options = new String[0];
	}

	System.out.println("");
	System.out.println("Choose your option:");
	for (int i = 0; i < options.length; i++) {
	    String option = options[i];
	    System.out.println(i + 1 + ". " + option);
	}
	return getNumberFromUser(1, options.length);
    }
}
