package examples.files;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author blecherl
 */
public class Person implements Serializable {

    private static long serialVersionUID = 505L;
    
//    boolean isOther;
    String lastName;
    String firstName;
    Date dateOfBirth;
    long id;

    public static Person create(String firstName, String lastName) {
        return new Person(firstName, lastName, new Date());
    }

    private Person(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
//        this.isOther = true;
        

        System.out.println("Create: " + this);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " is Other: ";//+ isOther + " (" + dateOfBirth + ")";
    }
}
