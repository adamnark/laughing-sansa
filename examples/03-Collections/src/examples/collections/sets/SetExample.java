package examples.collections.sets;

import examples.object.clone.Student;
import java.util.*;

/**
 *
 * @author blecherl
 */
public class SetExample {
    public static void main(String[] args) {
        Cat mizi = new Cat ("mizi", 4);
        Cat streetCat = new Cat ("mizi", 8);

        List<Cat> catsList = new ArrayList<Cat>();
        catsList.add(mizi);
        catsList.add(streetCat);
        printNumberOfCats(catsList);

        Set<Cat> cats = new HashSet<Cat>();
        cats.add(mizi);
        cats.add(streetCat);
        printNumberOfCats(cats);
    }

    private static void printNumberOfCats(Collection<Cat> cats) {
        System.out.println("Number of cats in " + cats.getClass().getSimpleName() + " = " + cats.size());
    }
}
