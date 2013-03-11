package examples.collections.persons;

public class Person implements Comparable<Person> {

    private String name;
    private long id;

    public Person(String name, long id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Person p) {
        return name.compareTo(p.name);
    }

    @Override
    public String toString() {
        return name + ", id = " + id;
    }

    public long getID() {
        return id;
    }
}