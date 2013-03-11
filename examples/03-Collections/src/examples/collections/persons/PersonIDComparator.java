package examples.collections.persons;

import java.util.Comparator;

/**
 *
 * @author blecherl
 */
public class PersonIDComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return (int) (o1.getID() - o2.getID());
    }

}
