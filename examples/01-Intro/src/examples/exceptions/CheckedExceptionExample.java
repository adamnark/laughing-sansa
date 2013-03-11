package examples.exceptions;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author blecherl
 */
public class CheckedExceptionExample {
    public static void main(String[] args) {
        File file = new File ("c:\\System Volume Information");

        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.list());
        } catch (IOException iOException) {
            //IOException is a checked excpetion and since the method getCanonicalPath() declares
            //it might throw one, we have to wrap the method with a try/catch block
            System.out.println("Got an IOException: " + iOException.getMessage());
        } catch (SecurityException securityException) {
            //SecurityException is an un-checked excpetion
            //we don't have to catch it...
            System.out.println("Got an SecurityException: " + securityException.getMessage());
        }
    }
}