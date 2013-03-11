package examples.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class ExceptionExampleWithHandling {
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

        int num1=0, num2=0;
        double result=0;
        
        System.out.println("Enter 2 numbers: ");
        try {
            num1 = scanner.nextInt();
            num2 = scanner.nextInt();

            result = num1 / num2;
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("user entered strings: " + inputMismatchException.getMessage());
        } catch (ArithmeticException arithmeticException) {
            System.out.println("Cannot divide by 0: " + arithmeticException.getMessage());
        } catch (Exception e) {
            System.out.println("For all other exceptions!");
        } 
        finally {
            System.out.println("In any case - get here.");
        }
        System.out.println(num1 + " / " + num2 + " = " + result);
    }
}
