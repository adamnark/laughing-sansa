package console.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author adamnark
 */
public class InputUtils {

    public static String readLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean ok = false;
        String line = null;
        do {
            try {
                line = br.readLine();
                ok = true;
            } catch (IOException ex) {
                System.out.println("Please try again:");
            }
        } while (!ok);

        return line;
    }

    public static int readInteger(int min, int max) {
        int val = -1;
        boolean ok = false;
        do {
            String line = readLine();
            Scanner sc = new Scanner(line);
            try {
                val = sc.nextInt();
            } catch (Exception ex) {
                System.out.println("bad integer, try again.");
            }

            if (val < min || val > max) {
                System.out.println("value out of range. input a number between " + min + " and " + max);
            } else {
                ok = true;
            }
        } while (!ok);
        
        return val;
    }
}
