package examples.object.equals;

/**
 *
 * @author blecherl
 */
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + '}';
    }
    
    

//    @Override
//    public boolean equals(Object obj) {
//
//        //if the other object is null - and this object is definitly not null
//        //return false
//        if (obj == null) {
//            return false;
//        }
//
//        //compare the other object Class to mine
//        //if we are not the same exact class - return false
//        //another option was to use
////        if (!(obj instanceof Person)) {
//        if (this.getClass() != obj.getClass()) {
//            return false;
//        }
//
//        //if we got here then obj is of type Person
//        //we can case it to another reference in order to easily compare
//        //between selected data members
//        final Person other = (Person) obj;
//
//        //compare the data member name (it might be null so we must check first)
//        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
//            return false;
//        }
//
//        //everything matches - the class and the name - return true
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 53 * hash + this.age;
//        return hash;
//    }
    
    
}
