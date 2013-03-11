package examples.files;


import java.io.*;
import java.util.*;

public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Construct an ArrayList of persons:
//        ArrayList<Person> people = new ArrayList<Person>();
//        people.add(Person.create("Jack", "Shephard"));
//        people.add(Person.create("Kate", "Austen"));
//
//        // Write the list into the file:
//        ObjectOutputStream out = new ObjectOutputStream(
//                new FileOutputStream("survivors.dat"));
//        out.writeObject(people);
//        out.flush();
//        out.close();

        // Read the vector from the file
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("survivors.dat"));

        // we know that we read vector of Persons
        ArrayList<Person> survivorsFromFile = (ArrayList<Person>) in.readObject();

//        Person ben = (Person) in.readObject();

        in.close();
////
////        // Print the 2 ArrayLists:
//        System.out.println("people = " + people);
        System.out.println("survivorsFromFile = " + survivorsFromFile);
//        System.out.println("Evil is: " + ben);
    }
}