package examples.collections.persons;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blecherl
 */
public class SortetListMain {

    public static void main(String[] args) {
        List<Person> personsList = new LinkedList<Person>();

        //using generics doesn guarentee that your collection will be safe
        //in this example there is another list pointing to the same
        //list of Person but without any generics
        //this is OK syntax-wise, but can result in a runtime exception
        //List objs = personsList;
        //objs.add("String");

        personsList.add(new Person("John", 9999));
        personsList.add(new Person("Paul", 7777));
        personsList.add(new Person("George", 1002));
        personsList.add(new Person("Ringo", 1002));

        System.out.print("Original: ");
        System.out.println(personsList);

        //Let's sort the collection

        // first option
        //if the collection data members implemented the
        //Comparable interface the you can sort the collection
        //directly
        Collections.sort(personsList);
        System.out.print("Sorted by name: ");
        System.out.println(personsList);

        //second option - use an extenal comparator
        Collections.sort(personsList, new PersonIDComparator());

//        // third option
        Collections.sort(personsList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return (int) (p1.getID() - p2.getID());
            }
        });
        System.out.print("Sorted by ID: ");
        System.out.println(personsList);

    }
}
