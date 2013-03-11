package examples.collections.sets;

/**
 *
 * @author blecherl
 */
public class Cat {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cat other = (Cat) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
//        if (this.age != other.age) {
//            return false;
//        }
        return true;
    }

//    implementing only the equlas method will result in two
//    cats with the same values in their data members to be added
//    twice to a HashSet
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + this.age;
        return hash;
    }
}
