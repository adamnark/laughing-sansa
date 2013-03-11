package examples.interfaces.housecleaning.humans;

import java.util.Date;

/**
 *
 * @author blecherl
 */
public class Person {

    private String name;
    private Date date;

    public Person(String name) {
        this(name, new Date());
    }

    public Person(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
