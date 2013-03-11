package examples.exceptions;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class ExceptionExample {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num1, num2;
        double result;

        System.out.println("Enter 2 numbers: ");
        num1 = scanner.nextInt();
        num2 = scanner.nextInt();

        result = num1 / num2;
        System.out.println(num1 + " / " + num2 + " = " + result);

        /*
         *  if the user will enter '9 0' we'll get the following exception:
         *
            Enter 2 numbers:
            9 0
            Exception in thread "main" java.lang.ArithmeticException: / by zero
                at examples.exceptions.ExceptionExample.main(ExceptionExample.java:20)
         */

        /*
         * if the user will enter '9 a' we'll get the following exception:
         *
            Enter 2 numbers:
            9 a
            Exception in thread "main" java.util.InputMismatchException
            at java.util.Scanner.throwFor(Scanner.java:840)
            at java.util.Scanner.next(Scanner.java:1461)
            at java.util.Scanner.nextInt(Scanner.java:2091)
            at java.util.Scanner.nextInt(Scanner.java:2050)
            at examples.exceptions.ExceptionExample.main(ExceptionExample.java:18)
         */

        //The paragraph above is called a StackStrace
        //it's a list of all the methods that were called starting from the executed
        //line (the one at the bottom)
    }
}
