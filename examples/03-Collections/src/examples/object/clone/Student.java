package examples.object.clone;

import java.util.Arrays;

/**
 *
 * @author blecherl
 *
 * In order to override the clone method from class Object
 * A class MUST implement the marker interface (meaning, an empty
 * interface) - Cloneable
 */
public class Student implements Cloneable{
    private String name;
    private int id;
    private int[] grades;

    public Student(String name, int id, int[] grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
        System.out.println("Created new Student: " + name);
    }

    public int[] getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id + ", grades=" + Arrays.toString(grades) + '}';
    }

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    //bad clone implementation - the grades array is not copied
    //only a reference to it is copied
//    @Override
//    public Student clone() throws CloneNotSupportedException {
//        return (Student) super.clone();
//    }

    //good clone implementation - the grades array is copied to a new array
    @Override
    public Student clone()  {
        try {
            Student newStudent = (Student) super.clone();
//          you can either use some utility methods such as Arrays.copyOf
            newStudent.grades = Arrays.copyOf(grades, grades.length);

//            but also - any array in Java implements the clone method too
//            newStudent.grades = grades.clone();
            return newStudent;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Student other = (Student) obj;
//        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
//            return false;
//        }
//        if (this.id != other.id) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
//        hash = 71 * hash + this.id;
//        return hash;
//    }


}
