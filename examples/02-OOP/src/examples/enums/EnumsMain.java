package examples.enums;

import java.util.Scanner;

/**
 *
 * @author blecherl
 */
public class EnumsMain {

    public static void main(String[] args) {
        System.out.println("Choose planet name:");
        for (SolarSystem s : SolarSystem.values()) {
            System.out.println(s.getOrdinalPosition() + ": " + s);
        }

        Scanner scanner = new Scanner(System.in);
        String userString = scanner.next();
        SolarSystem userEnum = SolarSystem.valueOf(userString.toUpperCase());

        System.out.println("Mass of " + userEnum + " is " + userEnum.getMass());
    }
}
